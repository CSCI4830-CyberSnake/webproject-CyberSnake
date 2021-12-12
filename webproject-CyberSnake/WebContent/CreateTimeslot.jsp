<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="util.UtilAccount"%>
<%@ page import="util.UtilEvent"%>
<%@ page import="datamodel.Account"%>
<%@ page import="datamodel.Event"%>

<!DOCTYPE html>
<html>

<head>
  <title>Create Timeslot Page</title>
  <link rel="stylesheet" href="styles.css" type="text/css">
</head>

<body>

<div id="container">

    <div id="header">
      <h1>CyberSnake</h1>
      <div id="nav">
        <ul class="nav_links">
         <li><a href="ChooseEventTimeslot.jsp">Back</a></li>
          <li><a href="UserAccount.jsp">Cancel</a></li>
        </ul>
      </div>
    </div>
    <div id="centerContent">
    
    <% 
      String eventId = request.getParameter("eventId");
      Event event = UtilEvent.getEvent(Integer.parseInt(eventId));
      %>

      <div id="block">
        <h1>Time-slot Creation</h1>
      </div>
      <p>Convention Center Hours of Operation: <strong>Everyday 06:00AM - 12:00AM</strong></p>
      <% if(!request.getParameterMap().containsKey("valid")) { %>
      	<em>**Make sure to input the correct format, and within the hours of operation**</em><br>
      	date: <em>YYYY-MM-DD</em><br>
      	startTime: <em>HH:MM</em>  (24hr format)<br>
      	endTime: <em>HH:MM</em>  (24hr format)<br>
      <% } %>
      
      <form action="AddTimeslot" method="POST">
      
        <div id="block">Date: <input type="date" name="date" required> <br /></div>
        <div id="block">Start Time: <input type="time" name="startTime" required> <br /></div>
        <div id="block">End Time: <input type="time" name="endTime" required> <br /></div>
        <div id="block">Occupancy: <input type="number" name="occupancy" min="0"> <br /></div>
        <input type="hidden" name="eventId" value="<%=eventId %>"/>
        <div id="block"><input type="submit" value="Create Timeslot" /></div>
  
      </form>
    </div>

</div>
</body>

</html>