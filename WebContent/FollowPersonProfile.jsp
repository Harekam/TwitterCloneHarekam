<%@page import="com.csc.applicationLogic.*" import="com.csc.bean.*"
	import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%
	String userFullName = null;
	String todo = "Follow";
	String changestatement = "to get";
	int tweetCount = 0, followingCount = 0, followerCount = 0;
	String user = null;
	Vector<PersonBean> vpbUserTweets = new Vector<PersonBean>();
	user = (String) session.getAttribute("userLogin");
	boolean isfollowing = false;
	if (session.getAttribute("userLogin") == null) {
		request.setAttribute("errorLogMsg",
				"Please Login first to continue!");
		RequestDispatcher rd = request
				.getRequestDispatcher("Login.jsp");
		rd.forward(request, response);
	} else {
		PersonBean pb = (PersonBean) session
				.getAttribute("followUserProfile");
		vpbUserTweets = (Vector<PersonBean>) session
				.getAttribute("followUserTweets");
		userFullName = pb.getFullName();
		tweetCount = (Integer) session
				.getAttribute("ClickedTweetCount");
		followingCount = (Integer) session
				.getAttribute("ClickedFollowingCount");
		followerCount = (Integer) session
				.getAttribute("ClickedFollowerCount");
		//followingid = pb.getFollowingId();

		isfollowing = pb.isFollowing();
	}
%>
<head>
<meta charset="ISO-8859-1">
<title><%=userFullName%></title>
<link rel="stylesheet" type="text/css" href="css/cshome.css" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<script type="text/javascript" src="javascripts/validatehomepage.js"></script>
</head>
<body>

	<%
		//if isfollowing is true then change the value of "todo" and "changestatement"
		if (isfollowing) {
			todo = "Unfollow";
			changestatement = "to stop getting";
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
						<td><%=tweetCount%>&nbsp;Tweets</td>
					</tr>
					<tr>
						<td><%=followingCount%>&nbsp;Following</td>
					</tr>
					<tr>
						<td><%=followerCount%>&nbsp;Followers</td>
					</tr>
				</table>
			</form>

		</div>
		<div>
			<section class="upstatus">
				<p></p>



				<form method="post" action="ControllerServlet" name="htmlFormName">
					<input type="hidden" name="htmlFormName" value="formFollow" /> <label
						for="follow"><font size="5"><%=todo%>&nbsp;<%=userFullName%>&nbsp;<%=changestatement%>&nbsp;tweet
							updates </font> </label> <input type="submit" class="button" value="<%=todo%>"
						name="funf">
				</form>

				<table class="hovertable" style="margin-top: 2em">
					<%
						if (vpbUserTweets != null) {
					%>
					<tr>

						<th>Tweets</th>
						<th>Time</th>
					</tr>
					<%
						for (int i = 0; i < vpbUserTweets.size(); i++) {
					%>
					<tr onmouseover="this.style.backgroundColor='#ffffff';"
						onmouseout="this.style.backgroundColor='#c0deed';">

						<td><%=vpbUserTweets.elementAt(i).getMessage()%></td>
						<td><%=vpbUserTweets.elementAt(i).getTimestamp()%></td>
					</tr>

					<%
						}
							session.removeAttribute("followUserTweets");
						} else {
					%>
					<p style="margin-top: 4em">
						<font size="5"><%=userFullName%> has no tweets! </font>
					</p>

					<%
						}
					%>
				</table>





			</section>

		</div>


		<footer style="text-align: center;">
			<font size="4" color="white"> Twitter Clone </font>
		</footer>




	</div>
</body>
</html>