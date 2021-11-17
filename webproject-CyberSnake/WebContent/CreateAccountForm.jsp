<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

	<head>
  		<title>Main Page</title>
  		<link rel="stylesheet" href="styles.css" type="text/css">
	</head>

	<div id="container">

  		<body>
    		<div id="header">
      			<h1>Register</h1>
      			<div id="nav">
        			<ul class="nav_links">
          				<li><a href="main.html">Home</a></li>
        			</ul>
      			</div>
    		</div>
    		
    		
    	<div id="centerContent">
    	
    		<%
    		if (session.getAttribute("missingInfo") != null) {
    			session.setAttribute("missingInfo", null);
    		%>
    			<p>Not all needed fields were entered!</p>
    		<%
    		}
    		%>
    		<%
    		if (session.getAttribute("fieldLong") != null) {
    			session.setAttribute("fieldLong", null);
    		%>
    			<p>One or more fields entered were too long! (max 20 characters)</p>
    		<%
    		}
    		%>
    		<%
    		if (session.getAttribute("nameTaken") != null) {
    			session.setAttribute("nameTaken", null);
    		%>
    			<p>The username is already in use!</p>
    		<%
    		}
    		%>
			<table>
				<form action="CreateAccount" method="POST">
	
        			<div id="block">User-name: <input type="text" name="username"> <br /></div>
        			<div id="block">First Name: <input type="text" name="firstName"> <br /></div>
        			<div id="block">Last Name: <input type="text" name="lastNname"> <br /></div>
        			<div id="block">Address: <input type="text" name="address"> <br /></div>
        			<div id="block">City: <input type="text" name="city"> <br /></div>
        			<div id="block">State: <input type="text" name="state"> <br /></div>
        			<div id="block">Zip Code: <input type="text" name="zip"> <br /></div>
        			<div id="block">Email: <input type="text" name="email"> <br /></div>
        			<div id="block">Phone: <input type="text" name="phone"> <br /></div>
        			<div id="block">Password: <input type="password" name="password"> <br /></div>
        			<div id="block">Admin Code: <input type="text" name="admin"> <br /><em>**only for Admin Accounts</em></div>
        			<div id="block"><input type="submit" value="Create Account" /></div>
  
      			</form>
 			</table>
      
    	</div>

  		</body>
	</div>

</html>