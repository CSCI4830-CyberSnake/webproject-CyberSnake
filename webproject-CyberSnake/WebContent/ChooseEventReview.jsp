<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="util.UtilAccount"%>
<%@ page import="util.UtilRegister"%>
<%@ page import="util.UtilTimeslot"%>
<%@ page import="util.UtilEvent"%>

<%@ page import="datamodel.Account"%>
<%@ page import="datamodel.Register"%>
<%@ page import="datamodel.Timeslot"%>
<%@ page import="datamodel.Event"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Choose Event Review Page</title>
<link rel="stylesheet" href="styles.css" type="text/css">
</head>

<body>

<div id="container">

		<div id="header">
			<h1>CyberSnake</h1>
			<div id="nav">
				<ul class="nav_links">
					<li><a href="UserAccount.jsp">Cancel</a></li>
				</ul>
			</div>
		</div>
		<div id="content">

			<div id="block">
				<h1>Review an Event</h1>
			</div>

			<form action="CreateReview.jsp" method="POST">
				<div id="block">
					<p>Please select an event to review from your passed events:</p>
				</div>

				<% 
				Account account = (Account) session.getAttribute("account");
				List<Register> passedRegs = UtilRegister.listPassed(account.getUsername());
				
				if( passedRegs.size() == 0 ) { %>
					<div id="block">
						You have no events to review! You'll be able to review an event once you attend one.<br>
					</div>
				<% } else { 
					int counter = 1;
					for(Register reg: passedRegs) { 
						Timeslot timeslot = UtilTimeslot.getTimeslot(reg.getTimeslotId());
						Event event = UtilEvent.getEvent(timeslot.getEventId()); %>

						<div id="block">
							<input type="radio" id="<%= counter %>" name="eventId" value="<%= event.getEventId() %>" required> <label
								for="<%= counter %>"><%= event.getName() %></label><br>
						</div>
				
				<% 	counter++;
					}
				} %>

				<br> <input type="submit" value="Submit">
			</form>

		</div>

</div>
</body>

</html>

