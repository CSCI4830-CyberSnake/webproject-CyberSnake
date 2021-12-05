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
<title>Choose Event Register Page</title>
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
				<h1>Event Registration</h1>
			</div>

			<%
			List<Event> events = UtilEvent.getActiveEvents();
			if( events.size() == 0 ) { %>
				<div id="block">
					<p>There are no active events to register to!</p>
				</div>
			<% } else { %>

			<form action="ChooseTimeslotRegister.jsp" method="POST">
				<div id="block">
					<p>Please select an event to register:</p>
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