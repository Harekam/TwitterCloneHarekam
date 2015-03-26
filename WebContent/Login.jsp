<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/global.css" />
<script type="text/javascript" src="javascripts/validatelog.js"></script>

</head>

<body>
	<%
		String errorMsg = (String) request.getAttribute("errorLogMsg");
		String successMsg = (String) request.getAttribute("successMsg");
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
			<p style="color: red">
				*<%=errorMsg%></p>
			<%
				}  if (successMsg!=null) {
			%>
			<p style="color: green">
				<%=successMsg%></p>
			<%
				}
			%>

			<p id="errorDisplay" style="color: red"></p>

			<fieldset>
				<legend>
					<font size="4"><b>Login</b></font>
				</legend>
				<form name="formlog" method="post" onsubmit="return validate()"
					action="ControllerServlet">
					<input type="hidden" name="htmlFormName" value="formlog" />
					<table>


						<tr>
							<td></td>
						</tr>

						<tr>

							<td><input class="tbd" type="text" name="username"
								placeholder="Email or username"></td>
						</tr>
						<tr>

							<td><input class="tbd" type="password" name="inpassword"
								placeholder="Password"></td>
						</tr>
						<tr>

							<td align="right"><input class="button" type="submit"
								name="submit" value="Login"></td>
						</tr>
						<tr>
							<td><br></td>
						</tr>
						<tr>
							<td>New User?&nbsp;<a class="achange" href="ControllerServlet?out=signup">Signup</a></td>
						</tr>
					</table>

				</form>
			</fieldset>

		</div>

	</div>
</body>
</html>