<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="util.UtilAccount"%>
<%@ page import="datamodel.Account"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Validate Login</title>
<link rel="stylesheet" href="styles.css" type="text/css">
</head>
<body>
	<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	Account account = UtilAccount.getAccount(username);
	
	if( account!= null && account.getPassword().equals(password)){
		session.setAttribute("account", account);
		response.sendRedirect("UserAccount.jsp");
	}
	else {	%>

	<div id="container">

		<div id="header">
			<h1>CyberSnake</h1>
			<div id="nav">
				<ul class="nav_links">
					<li><a href="login.html">Log In</a></li>
					<li><a href="">Create Account</a></li>
				</ul>
			</div>
		</div>
		<div id="centerContent">

			<h2>Unable to Log In</h2>
			<% if (account == null) { %>
			<p>The username given does not match with any existing account.</p>
			<% } else if (!account.getPassword().equals(password)){ %>
			<p>You entered an incorrect password.</p>
			<% } %>
		</div>

	</div>
	<%} %>
</body>
</html>