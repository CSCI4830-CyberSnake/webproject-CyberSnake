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
<title>User Account</title>
<link rel="stylesheet" href="styles.css" type="text/css">
</head>

<body>
	<div id="container">
		<div id="header">
			<h1>CyberSnake</h1>
			<%
				Account account = (Account) session.getAttribute("account");
			%>
			<div id="nav">
				<ul class="nav_links">
					<% if( account.getAdmin() ) {%>
						<li><a href="">Create Event</a></li>
						<li><a href="">Add Time slot</a></li>
					<% } %>
					<li><a href="">Register</a></li>
					<li><a href="">Cancel Registration</a></li>
					<li><a href="">Make Review</a></li>
					<li><a href="/webproject-CyberSnake/Logout">Log Out</a></li>
				</ul>
			</div>
		</div>
		<div id="content">
			<div id="block">
				<h1>Hello, <%= account.getFirstName() %>!<br></h1>
			</div>
			
			<div id="block">
				<h2>My Registered Events:</h2>
			</div>
			
			<% 	List<Register> listRegisters = UtilRegister.listRegisters(account.getUsername()); 
				if(listRegisters.size()==0) { %>
					<p>You have not registered for any events yet!</p>
			<% } else {%>	
				<table>
					<tr>
						<th>Event Name</th>
						<th>Date</th>
						<th>Time</th>
					</tr>
					
					<% 	for(Register register: listRegisters) {
							Timeslot timeslot = UtilTimeslot.getTimeslot(register.getTimeslotId());
							Event event = UtilEvent.getEvent(timeslot.getEventId());
							%>
							<tr>
								<td><%= event.getName() %></td>
	          					<td><%= timeslot.getDate() %></td>
	          					<td><%= timeslot.getTime() %></td>
	          				</tr>
					<% } %>
				</table>
			<% } %>
			
			<% 	if( account.getAdmin() ) {%>
					<div id="block">
						<h2>My Created Events:</h2>
					</div>					
			
					<% List<Event> events = UtilEvent.listEvents(account.getUsername());
					if( events.size()==0) {%>
						<p>You have not created any events yet!</p>
					<% } else {%>
						<table>
							<tr>
								<th>Event Name</th>
							</tr>
							
							<% 	for(Event event: events) {%>
									<tr>
										<td><%= event.getName() %></td>
			          				</tr>
							<% } %>
						</table>					
					<% } %>
					
			<% 	} %>

		</div>

	</div>
</body>

</html>