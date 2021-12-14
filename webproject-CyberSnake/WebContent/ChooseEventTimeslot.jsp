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
<title>Choose Event Timeslot Page</title>
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
				<h1>Time-slot Creation</h1>
			</div>

			<%
			Account account = (Account) session.getAttribute("account");
			List<Event> events = UtilEvent.listEvents(account.getUsername());
			if( events.size() == 0 ) { %>
				<div id="block">
					<p>There are no events to choose from! Make sure to create an event first!</p>
				</div>
			<% } else { %>

			<form action="CreateTimeslot.jsp?valid=true" method="POST">
				<div id="block">
					<p>Please select an event for the time-slot:</p>
				</div>
				
				<% 
				int counter = 1;
				for( Event event: events ) { %>

					<div id="block">
						<input type="radio" id="<%= counter %>" name="eventId" value="<%= event.getEventId() %>" required> <label
							for="<%= counter %>"><%= event.getName() %></label><br>
					</div>
					
				<% 
					counter++;
				} 
				%>

				<br> <input type="submit" value="Submit">
			</form>
			
			<% } %>

		</div>

	</div>
</body>

</html>