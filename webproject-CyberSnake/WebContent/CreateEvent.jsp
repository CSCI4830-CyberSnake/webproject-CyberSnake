<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <title>Create Event Page</title>
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
    <div id="centerContent">

      <div id="block">
        <h1>Create a New Event</h1>
      </div>
      
      <form action="AddEvent" method="POST">
	
        <div id="block">Event Name: <input type="text" name="name"> <br /></div>
        <p>Description</p>
        <textarea name="description" rows="5" cols="50"></textarea>
        <div id="block"><input type="submit" value="Create Event" /></div>
  
      </form>
    </div>

</div>
</body>

</html>