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
<title>Choose Event Cancel Page</title>
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
				<h1>Cancel Event Registration</h1>
			</div>
			
			<%
			Account account = (Account) session.getAttribute("account");
			List <Timeslot> ts = UtilRegister.listUpcoming(account.getUsername());
		
			if(ts.isEmpty()) { %>
				<div id="block">
					<p>There are no active events to cancel!</p>
				</div>
			<% } else { %>

			<form action="CancelRegistration" method="POST">
				<div id="block">
					<p>Please select an event to cancel:</p>
				</div>
				
				<% 
				int counter = 1;
				for( Timeslot t : ts) { 
					Event event = UtilEvent.getEvent(t.getEventId());%>

					<div id="block">
						<input type="radio" id="<%= counter %>" name="timeslotId" value="<%= t.getTimeslotId() %>" required> <label
							for="<%= counter %>"><%= event.getName() %> - <%= t.getDate().toString() %> | <%= UtilTimeslot.getFormattedTime(t.getStartTime(), t.getEndTime()) %></label><br>
					</div>
					
				<%
				} 
				%>

				<br> <input type="submit" value="Submit">
			</form>
			
			<% } %> 

		</div>

	</div>
</body>

</html>