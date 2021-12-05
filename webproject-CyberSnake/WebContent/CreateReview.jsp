<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="util.UtilAccount"%>
<%@ page import="util.UtilEvent"%>
<%@ page import="datamodel.Account"%>
<%@ page import="datamodel.Event"%>
    
<!DOCTYPE html>
<html>

<head>
  <title>Create Review Page</title>
  <link rel="stylesheet" href="styles.css" type="text/css">
</head>
<body>
<div id="container">

    <div id="header">
      <h1>CyberSnake</h1>
      <div id="nav">
        <ul class="nav_links">
          <li><a href="ChooseEventReview.jsp">Back</a></li>
          <li><a href="UserAccount.jsp">Cancel</a></li>
        </ul>
      </div>
    </div>
    <div id="content">
    
      <% 
      String eventId = request.getParameter("eventId");
      Event event = UtilEvent.getEvent(Integer.parseInt(eventId));
      %>

      <div id="block">
        <h1>Create a Review for <%= event.getName() %></h1>
      </div>

      <form action="AddReview" method="POST">
        <div id="block"><p>Rate the Event:</p></div>
        
        <div id="block"><input type="radio" id="1" name="stars" value="1" required>
          <label for="1">
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
          </label><br></div>

        <div id="block"><input type="radio" id="2" name="stars" value="2">
          <label for="2">
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
          </label><br></div>
        
        <div id="block"><input type="radio" id="3" name="stars" value="3">
          <label for="3">
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
          </label></div>

        <div id="block"><input type="radio" id="4" name="stars" value="4">
          <label for="4">
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star"></span>
          </label></div>

        <div id="block"><input type="radio" id="5" name="stars" value="5">
          <label for="5">
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star checked"></span><br>
          </label></div>
        
        <div id="block"><p>Add a Comment:</p></div>
        <textarea name="comment" rows="5" cols="50"></textarea>

        <div id="block"><input type="radio" id="anon" name="anon" value="1">
          <label for="anon"></label>-Anonymous?</div>
          
        <input type="hidden" name="eventId" value="<%=eventId %>"/>

        <input type="submit" value="Submit">

      </form>

    </div>

</div>
</body>

</html>