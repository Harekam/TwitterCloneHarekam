<%@page import="com.csc.applicationLogic.*" import="com.csc.bean.*"
	import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Following</title>
<link rel="stylesheet" type="text/css" href="css/cshome.css" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<script type="text/javascript" src="javascripts/validatehomepage.js"></script>

</head>
<body>



	<%
		String userFullName = null, errorMsg = null, successMsg = null;
		int tweetCount = 0, followingCount = 0, followerCount = 0;
		String user = null;
		Vector<PersonBean> vpbFollowing = new Vector<PersonBean>();
		vpbFollowing = null;
		user = (String) session.getAttribute("userLogin");
		if (session.getAttribute("userLogin") == null) {
			request.setAttribute("errorLogMsg",
					"Please Login first to continue!");
			RequestDispatcher rd = request
					.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		} else {
			vpbFollowing = (Vector<PersonBean>) session
					.getAttribute("followingOfCurrent");
			userFullName = (String) session.getAttribute("fullname");
			tweetCount = (Integer) session
					.getAttribute("CurrentTweetCount");
			followingCount = (Integer) session
					.getAttribute("CurrentFollowingCount");
			followerCount = (Integer) session
					.getAttribute("CurrentFollowerCount");
		}
	%>


	<div class="container">
		<header class="heading">



			<table style="float: right;">
				<tr>

					<th><font color="white"><%=userFullName%></font></th>
					<th><a class="headerClicks"
						href="ControllerServlet?input=home">Home</a></th>
					<th><a class="headerClicks"
						href="ControllerServlet?input=profile">Profile</a></th>
					<th><a class="headerClicks"
						href="ControllerServlet?input=signout">Signout</a></th>
				</tr>
			</table>

		</header>
		<div class="nav" style="text-align: left;">

			<p id="errorSearch" style="color: red"></p>
			<form name="formSearch" action="ControllerServlet" method="post"
				onsubmit="return validateSearch()">
				<input type="hidden" name="htmlFormName" value="formSearch" />
				<table>
					<tr>

						<td>Follow</td>

					</tr>
					<tr>
						<td><input class="tdbhome" type="search" name="search"
							placeholder="search person"></td>
					</tr>
					<tr>
						<td><br><br><br></td>
					</tr>
					<tr>
						<td><a class="countLinks" href="ControllerServlet?input=profile"><span
								style="text-decoration: underline;"><%=tweetCount%></span>&nbsp;Tweets</a></td>
					</tr>
					<tr>
						<td><%=followingCount%>&nbsp;Following</td>
					</tr>
					<tr>
						<td><a class="countLinks" href="ControllerServlet?input=followers"><span
								style="text-decoration: underline;"><%=followerCount%></span>&nbsp;Followers</a></td>
					</tr>
				</table>
			</form>

		</div>
		<div>
			<section class="upstatus">
				<%
					if (vpbFollowing != null) {
				%>
				<p>Click on username to visit that user's profile</p>
				<table class="hovertable">
					<tr>
						<th>Full Name</th>
						<th>Username</th>
						<th>Email</th>
					</tr>
					<%
						for (int i = 0; i < vpbFollowing.size(); i++) {
					%>
					<tr onmouseover="this.style.backgroundColor='#ffffff';"
						onmouseout="this.style.backgroundColor='#c0deed';">
						<td><%=vpbFollowing.elementAt(i).getFullName()%></td>
						<td><a class="tweetUserProfile"
							href="ControllerServlet?userid=<%=vpbFollowing.elementAt(i).getUserName()%>"><%=vpbFollowing.elementAt(i).getUserName()%></a></td>
						<td><%=vpbFollowing.elementAt(i).getEmail()%></td>
					</tr>

					<%
						}
							session.removeAttribute("followingOfCurrent");
					%>
				</table>

				<%
					} else {
				%>
				
					<p>
						<font size="5" color="red">You are not following anyone!</font>
					</p>
			
				<%
					}
				%>
			</section>

		</div>


		<footer style="text-align: center;">
			<font size="4" color="white"> Twitter Clone </font>
		</footer>




	</div>
</body>
</html>