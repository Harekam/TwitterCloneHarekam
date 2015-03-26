<%@page import="com.csc.applicationLogic.*" import="com.csc.bean.*"
	import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="css/cshome.css" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<script type="text/javascript" src="javascripts/validatehomepage.js"></script>
</head>
<body>



	<%
		String userFullName = null, errorMsg = null, successMsg = null;
		int tweetCount = 0, followingCount = 0, followerCount = 0;
		String user = null;
		Vector<PersonBean> vpbAllTweets = new Vector<PersonBean>();
		user = (String) session.getAttribute("userLogin");
		if (session.getAttribute("userLogin") == null) {
			request.setAttribute("errorLogMsg",
					"Please Login first to continue!");
			RequestDispatcher rd = request
					.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);

		} else {

			tweetCount = (Integer) session
					.getAttribute("CurrentTweetCount");
			followingCount = (Integer) session
					.getAttribute("CurrentFollowingCount");
			followerCount = (Integer) session
					.getAttribute("CurrentFollowerCount");
			vpbAllTweets = (Vector<PersonBean>) session
					.getAttribute("allTweets");
			userFullName = (String) session.getAttribute("fullname");
			errorMsg = (String) session.getAttribute("msgErrorTweet");
			successMsg = (String) session.getAttribute("msgSuccessTweet");

		}
	%>


	<div class="container">
		<header class="heading">


			<table style="float: right;">
				<tr>

					<th><font color="white">Welcome <%=userFullName%></font></th>
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
						<td><br> <br> <br></td>
					</tr>

					<tr>
						<td><a class="countLinks"
							href="ControllerServlet?input=profile"><span
								style="text-decoration: underline;"><%=tweetCount%></span>&nbsp;Tweets</a></td>
					</tr>
					<tr>
						<td><a class="countLinks"
							href="ControllerServlet?input=following"><span
								style="text-decoration: underline;"><%=followingCount%></span>&nbsp;Following</a></td>
					</tr>
					<tr>
						<td><a class="countLinks"
							href="ControllerServlet?input=followers"><span
								style="text-decoration: underline;"><%=followerCount%></span>&nbsp;Followers</a></td>
					</tr>
				</table>
			</form>

		</div>
		<div>
			<section class="upstatus">
				<%
					if (errorMsg != null) {
				%>
				<p style="color: red">
					*<%=errorMsg%></p>
				<%
					session.removeAttribute("msgErrorTweet");
					}
					if (successMsg != null) {
				%>
				<p style="color: green">
					<%=successMsg%></p>
				<%
					session.removeAttribute("msgSuccessTweet");
					}
				%>
				<p id="errorDisplay" style="color: red"></p>
				<form name="formHomeUpdate" method="post" action="ControllerServlet"
					onsubmit="return validateTweet()">
					<input type="hidden" name="htmlFormName" value="formHomeUpdate" />
					<label for="intext"><font size="4">What are you
							doing?</font></label>
					<textarea onkeyup="textAreaAdjust(this)" style="overflow: hidden"
						id="intext" autofocus="autofocus" rows="4" cols="50"
						maxlength="140" name="tweetArea" id="AreaTweet"></textarea>
					<br> <input style="float: right;" type="submit" name="update"
						class="button" value="Update">
				</form>

				<div style="margin-top: 3em">

					<%
						if (vpbAllTweets != null) {
					%>
					<p>Click on username to visit that user's profile</p>
					<table class="hovertable">
						<tr>
							<th>Username</th>
							<th>Tweet</th>
							<th>Time</th>
						</tr>


						<%
							for (int i = 0; i < vpbAllTweets.size(); i++) {
						%>
						<tr onmouseover="this.style.backgroundColor='#ffffff';"
							onmouseout="this.style.backgroundColor='#c0deed';">
							<td><a class="tweetUserProfile"
								href="ControllerServlet?userid=<%=vpbAllTweets.elementAt(i).getUserName()%>"><%=vpbAllTweets.elementAt(i).getUserName()%></a></td>
							<td><%=vpbAllTweets.elementAt(i).getMessage()%></td>
							<td><%=vpbAllTweets.elementAt(i).getTimestamp()%></td>
						</tr>

						<%
							}
								session.removeAttribute("allTweets");
							}
						%>

					</table>



				</div>

			</section>

		</div>


		<footer style="text-align: center;">
			<font size="4" color="white"> Twitter Clone </font>
		</footer>




	</div>
</body>
</html>