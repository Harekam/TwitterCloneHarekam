<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Signup</title>
<link rel="stylesheet" type="text/css" href="css/global.css" />
<script type="text/javascript" src="javascripts/validatesignup.js"></script>
</head>

<body>
	<%
		String errorMsg = (String) request.getAttribute("errorSignMsg");
		if (session.getAttribute("userLogin") != null) {
			response.sendRedirect("HomePage.jsp");
		} 
	%>
	<h1 align="center">
		<img src="images/twitter.png" width="70" height="50">Welcome to
		Twitter Clone
	</h1>
	<hr>
	<div class="vertical-container">

		<div class="vertically-centered">
			<%
				if (errorMsg != null) {
			%>
			<p id="errorDisplay" style="color: red">
				*<%=errorMsg%>
			</p>
			<%
				}
			%>
			<p id="errorDisplay" style="color: red"></p>
			<form name="formsign" method="post" onsubmit="return validate()"
				action="ControllerServlet">
				<input type="hidden" name="htmlFormName" value="formsign" />
				<fieldset style="width: 350">
					<legend>
						<font size="4"><b>Your Details</b></font>
					</legend>
					<table>


						<tr>
							<td></td>
						</tr>


						<tr>

							<td><input class="tbd" type="text" name="susername"
								placeholder="Username"></td>
						</tr>
						<tr>

							<td><input class="tbd" type="password" name="spassword"
								placeholder="Password"></td>
						</tr>
						<tr>

							<td><input class="tbd" type="text" name="fullname"
								placeholder="Full name"></td>
						</tr>
						<tr>

							<td><input class="tbd" type="email" name="semail"
								placeholder="Email"></td>
						</tr>
						<tr>

							<td align="right"><input class="button" type="submit"
								name="submit" value="Signup"></td>
						</tr>
						<tr>
							<td><br></td>
						</tr>
						<tr>
							<td>Already a User?&nbsp;<a href="ControllerServlet?out=login" class="achange">Login</a></td>
						</tr>
					</table>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>