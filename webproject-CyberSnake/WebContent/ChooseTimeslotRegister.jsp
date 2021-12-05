<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="util.UtilAccount"%>
<%@ page import="util.UtilRegister"%>
<%@ page import="util.UtilTimeslot"%>
<%@ page import="util.UtilEvent"%>
<%@ page import="util.UtilReview"%>

<%@ page import="datamodel.Account"%>
<%@ page import="datamodel.Register"%>
<%@ page import="datamodel.Timeslot"%>
<%@ page import="datamodel.Event"%>
<%@ page import="datamodel.Review"%>

<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>

<head>
<title>Choose Time-slot Page</title>
<link rel="stylesheet" href="styles.css" type="text/css">
</head>
<body>
	<div id="container">

		<div id="header">
			<h1>CyberSnake</h1>
			<div id="nav">
				<ul class="nav_links">
					<li><a href="ChooseEventRegister.jsp">Back</a></li>
					<li><a href="UserAccount.jsp">Cancel</a></li>
				</ul>
			</div>
		</div>
		<div id="content">

			<% 
			String eventId = request.getParameter("eventId"); 
			Event event = UtilEvent.getEvent(Integer.parseInt(eventId));
			List<Timeslot> timeslots = UtilTimeslot.getActiveTimeslotsByEvent(Integer.parseInt(eventId));
			%>

			<div id="block">
				<h1>Event Registration <%= event.getName() %></h1>
				<p><%= event.getDescription() %></p>
			</div>
			
			<% 
			if(timeslots.size() == 0) { %>
				<div id="block">
					<p>There are no active time-slots for this event!</p>
				</div>
			<% } else { %>

			<form action="RegisterEvent" method="POST">
				<div id="block">
					<p>Choose an available time-slot:</p>
				</div>
				
				<% 
				int counter = 1;
				for( Timeslot timeslot: timeslots) {%>

					<div id="block">
						<input type="radio" id="<%= counter %>" name="timeslotId" value="<%= timeslot.getTimeslotId()%>" required> <label
							for="<%= counter %>"><%= timeslot.getDate().toString() %> | <%= UtilTimeslot.getFormattedTime(timeslot.getStartTime(), timeslot.getEndTime()) %></label><br>
					</div>
				
				<% } %>

				<br> <input type="submit" value="Submit">
			</form>
			
			<% } %>

			<div id="block">
				<h1>Reviews for <%= event.getName() %></h1>
			</div>
			
			<%
			List<Review> reviews = UtilReview.listReviews(eventId);
			if( reviews.size() == 0 ) { %>
				<div id="block">
					<p>There are no reviews for this event!</p>
				</div>			
			<% } else {
				for( Review review: reviews ) { 
					int checked = review.getStars();
					int unchecked = 5 - checked; %>
					
					<div id="block">
					
					<%
					for(int i = 1; i <= checked; i++) { %>
						<span class="fa fa-star checked"></span>
					<% } 
					for(int i = 1; i <= unchecked; i++) { %>
						<span class="fa fa-star"></span>
					<% } %>

					<p><%= review.getComment() %></p>
					
					<% if(review.getAnon()) { %>
						<p>-Anonymous</p>
					
					<% } else { %>
						<p>-<%= review.getUsername() %></p>
					<% } %>
				</div>
			
			<% } 
			}
			%>

		</div>

	</div>
</body>

</html>