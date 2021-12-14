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
						<li><a href="CreateEvent.jsp">Create Event</a></li>
						<li><a href="ChooseEventTimeslot.jsp">Add Time slot</a></li>
					<% } %>
					<li><a href="ChooseEventRegister.jsp">Register</a></li>
					<li><a href="ChooseEventCancel.jsp">Cancel Registration</a></li>
					<li><a href="ChooseEventReview.jsp">Make Review</a></li>
					<li><a href="/webproject-CyberSnake/Logout">Log Out</a></li>
				</ul>
			</div>
		</div>
		<div id="content">
		
		<%
		int register = -1, cancel = -1, review = -1, event = -1, time = -1;
		
		if(request.getParameterMap().containsKey("register"))
	    	  register = Integer.valueOf(request.getParameter("register"));
		
		if(request.getParameterMap().containsKey("cancel"))
	    	  cancel = Integer.valueOf(request.getParameter("cancel"));
		
		if(request.getParameterMap().containsKey("review"))
	    	  review = Integer.valueOf(request.getParameter("review"));
		
		if(request.getParameterMap().containsKey("event"))
	    	  event = Integer.valueOf(request.getParameter("event"));
		
		if(request.getParameterMap().containsKey("time"))
	    	  time = Integer.valueOf(request.getParameter("time"));
		
		if(register == 0) { %>
			<div class="alert0">
	        <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
	        Success! You have successfully registered to an event!
	      	</div>
		<% }
		if(register == 1) { %>
			<div class="alert1">
	        <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
	        Warning! Event could no be registered!
	      	</div>
		<% }
		if(cancel == 0) { %>
			<div class="alert0">
	        <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
	        Success! You have successfully cancelled registration to an event!
	      	</div>
		<% }
		if(cancel == 1) { %>
			<div class="alert1">
	        <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
	        Warning! Registration to an event could not be cancelled!
	      	</div>
		<% }
		if(review == 0) { %>
			<div class="alert0">
	        <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
	        Success! You have successfully posted a review!
	      	</div>
		<% }
		if(review == 1) { %>
			<div class="alert1">
	        <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
	        Warning! Your review was not posted!
	      	</div>
		<% }
		if(event == 0) { %>
			<div class="alert0">
	        <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
	        Success! You have successfully created an event!
	      	</div>
		<% }
		if(event == 1) { %>
			<div class="alert1">
	        <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
	        Warning! Event could not be created!
	      	</div>
		<% }
		if(time == 0) { %>
			<div class="alert0">
	        <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
	        Success! You have successfully added a time-slot to an event!
	      	</div>
		<% }
		if(time == 1) { %>
			<div class="alert1">
	        <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
	        Warning! The time-slot could not be created!
	      	</div>
		<% }%>
			
			<div id="block">
				<h2>My Upcoming Events:</h2>
			</div>
			
			<% 	List<Timeslot> upcoming = UtilRegister.listUpcoming(account.getUsername()); 
				if(upcoming.size()==0) { %>
					<table>
						<tr>
							<th>You have no upcoming events!</th>
						</tr>
					</table>
			<% } else {%>	
				<table>
					<tr>
						<th>Event Name</th>
						<th>Date</th>
						<th>Time</th>
					</tr>
					
					<% 	for(Timeslot timeslot: upcoming) {
							Event ev = UtilEvent.getEvent(timeslot.getEventId());
							%>
							<tr>
								<td><%= ev.getName() %>
								<% if(UtilTimeslot.inNextHour(timeslot)) { %>
									<button class="button" type="button" onclick="alert('<%= ev.getName() %> starts in less than one hour!')">Checked In!</button>
								<% } %>
								</td>
	          					<td><%= timeslot.getDate() %></td>
	          					<td><%= UtilTimeslot.getFormattedTime(timeslot.getStartTime(), timeslot.getEndTime()) %></td>
	          				</tr>
					<% } %>
				</table>
			<% } %>
			
			<div id="block">
				<h2>My Passed Events:</h2>
			</div>
			
			<% 	List<Timeslot> passed = UtilRegister.listPassed(account.getUsername()); 
				if(passed.size()==0) { %>
					<table>
						<tr>
							<th>You have no passed events!</th>
						</tr>
					</table>
			<% } else {%>	
				<table>
					<tr>
						<th>Event Name</th>
						<th>Date</th>
						<th>Time</th>
					</tr>
					
					<% 	for(Timeslot timeslot: passed) {
							Event ev = UtilEvent.getEvent(timeslot.getEventId());
							%>
							<tr>
								<td><%= ev.getName() %></td>
	          					<td><%= timeslot.getDate() %></td>
	          					<td><%= UtilTimeslot.getFormattedTime(timeslot.getStartTime(), timeslot.getEndTime()) %></td>
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
						<table>
							<tr>
								<th>You have not created any events!</th>
							</tr>
						</table>
					<% } else {%>
						<table>
							<tr>
								<th>Event Name</th>
								<th>Time Slot</th>
							</tr>
							
							<% 	for(Event ev: events) {%>
									<tr>
										<td><%= ev.getName() %></td>
			         
			          				<% List<Timeslot> timeslots = UtilTimeslot.getTimeslotsByEvent(ev.getEventId()); 
			          				if (timeslots.size()==0) { %>
										<td>No active time slots!</td>
										
			          				<% } else { %>
			          						<td>
			          							<table>
			          								<tr>
			          									<th>Date</th>
			          									<th>Time</th>
			          									<th>Available Seats</th>
			          								</tr>
			          					
			          					<% for( Timeslot timeslot: timeslots) { %>
			          								<tr>
			          									<td><%= timeslot.getDate() %></td>
			          									<td><%= UtilTimeslot.getFormattedTime(timeslot.getStartTime(), timeslot.getEndTime()) %></td>
			          									<td><%= timeslot.getOccupancy() %></td>
			          								</tr>
			          					<% } %>
			          							</table>
			          						</td>
			          				<% } %>
			          				
							<% } %>
						</table>					
					<% } %>
					
			<% 	} %>

		</div>

	</div>
</body>

</html>