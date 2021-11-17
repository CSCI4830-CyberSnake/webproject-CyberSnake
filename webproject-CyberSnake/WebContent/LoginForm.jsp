<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<title>Login Page</title>
<link rel="stylesheet" href="styles.css" type="text/css">
</head>
<body>
	<div id="container">

		<div id="header">
			<h1>CyberSnake</h1>
			<div id="nav">
				<ul class="nav_links">
					<li><a href="main.html">Home</a></li>
					<li><a href="CreateAccountForm.jsp">Create Account</a></li>
				</ul>
			</div>
		</div>
		<div id="centerContent">

			<div id="block">
				<h1>Welcome Back!</h1>
			</div>

			<%
				if (session.getAttribute("noAccount") != null) {
					session.setAttribute("noAccount", null);
			%>
				<p>**The username given does not match with any existing account!**</p>
			<%
				}
			%>
			<%
				if (session.getAttribute("invalidPassword") != null) {
					session.setAttribute("invalidPassword", null);
			%>
				<p>**You entered an incorrect password!**</p>
			<%
				}
			%>

			<%
				//to clear any session attributes set prior
				if (session != null) {
					session.invalidate();
				}
			%>

			<form action="Login" method="POST">

				<div id="block">
					User-name: <input type="text" name="username"> <br />
				</div>
				<div id="block">
					Password: <input type="password" name="password"> <br />
				</div>
				<div id="block">
					<input type="submit" value="Log In" />
				</div>

			</form>
		</div>

	</div>
</body>
</html>