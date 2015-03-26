<%@page import="com.csc.applicationLogic.*" import="com.csc.bean.*"
	import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>


<meta charset="ISO-8859-1">
<title>Profile</title>
<link rel="stylesheet" type="text/css" href="css/cshome.css" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<script type="text/javascript" src="javascripts/validateprofile.js"></script>

</head>
<body>



	<%
		String userFullName = null;
		int tweetCount = 0, followingCount = 0, followerCount = 0;
		String user = null, errorMsg = null, successMsg = null, currentemail = null;
		String errorEditTweetMsg = null, successEditTweetMsg = null;
		user = (String) session.getAttribute("userLogin");
		Vector<PersonBean> vpbAllTweets = new Vector<PersonBean>();
		if (session.getAttribute("userLogin") == null) {
			request.setAttribute("errorLogMsg",
					"Please Login first to continue!");
			RequestDispatcher rd = request
					.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		} else {
			PersonBean pbLog = (PersonBean) session.getAttribute("login");
			currentemail = pbLog.getEmail();
			errorMsg = (String) session.getAttribute("errorEditMsg");
			successMsg = (String) session.getAttribute("successEditMsg");
			userFullName = (String) session.getAttribute("fullname");
			vpbAllTweets = (Vector<PersonBean>) session
					.getAttribute("CurrentUserTweets");
			tweetCount = (Integer) session
					.getAttribute("CurrentTweetCount");
			followingCount = (Integer) session
					.getAttribute("CurrentFollowingCount");
			followerCount = (Integer) session
					.getAttribute("CurrentFollowerCount");
			successEditTweetMsg = (String) session
					.getAttribute("editTweetSuccessMsg");
			errorEditTweetMsg = (String) session
					.getAttribute("editTweetErrorMsg");
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


			<form name="formSearch" action="ControllerServlet" method="post"
				onsubmit="return validateSearch()">

				<input type="hidden" name="htmlFormName" value="formSearch" />
				<p id="errorSearch" style="color: red"></p>
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
						<td><%=tweetCount%>&nbsp;Tweets</td>
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



				<fieldset>
					<legend>
						<font size="5"><b>Profile Settings</b></font>
					</legend>
					<%
						if (errorMsg != null) {
					%>
					<p id="errorDisplay" style="color: red">
						*<%=errorMsg%>
					</p>
					<%
						session.removeAttribute("errorEditMsg");
						} else if (successMsg != null) {
					%>
					<p id="successDisplay" style="color: green">
						*<%=successMsg%>
					</p>
					<%
						session.removeAttribute("successEditMsg");
						}
					%>
					<p id="errorEditDisplay" style="color: red"></p>


					<form id="formedit" name="formProfileUpdate" method="post"
						action="ControllerServlet" onsubmit="return validateeditpro()">
						<input type="hidden" name="htmlFormName" value="formProfileUpdate" />

						<table>

							<tr>
								<td><font size="4"><b>Fullname:&nbsp;</b></font></td>
								<td id="valfullname"><%=userFullName%></td>
								<td><input id="ShowEditFullname"
									style="width: 200px; display: none;" class="tbd" type="text"
									name="editFullName" placeholder="Fullname"
									value="<%=userFullName%>"></td>
							</tr>

							<tr>
								<td><font size="4"><b>Email:&nbsp;</b></font></td>
								<td id="valemail"><%=currentemail%></td>
								<td><input class="tbd" id="ShowEditEmail"
									style="width: 200px; display: none;" type="email"
									name="editEmail" placeholder="Email" value="<%=currentemail%>"></td>
							</tr>

							<tr>



								<td colspan="2" align="right"><input id="editdispbox"
									style="text-align: right;" type="button" name="update"
									class="button"
									onclick="document.getElementById('editdispbox').style.display='none';document.getElementById('submitupdatepro').style.display='';document.getElementById('cancelbuttoneditfe').style.display='';document.getElementById('valfullname').style.display='none';document.getElementById('valemail').style.display='none';document.getElementById('ShowEditFullname').style.display='';document.getElementById('ShowEditEmail').style.display='';"
									value="Edit"></td>
							</tr>
							<tr>


								<td colspan="2" align="right"><input type="submit"
									id="submitupdatepro" value="Edit" style="display: none"
									class="button">
									<Button type="button" id="cancelbuttoneditfe"
										style="display: none;"
										onclick="document.getElementById('editdispbox').style.display='';document.getElementById('submitupdatepro').style.display='none';document.getElementById('cancelbuttoneditfe').style.display='none';document.getElementById('valfullname').style.display='';document.getElementById('valemail').style.display='';document.getElementById('ShowEditFullname').style.display='none';document.getElementById('ShowEditEmail').style.display='none';return false;"
										class="button">Cancel</Button></td>
							</tr>
						</table>

						<div style="display: inline;">
							<Button id="changepassbutn"
								onclick="document.getElementById('deleteprofilebutn').style.display='none';document.getElementById('changepassbutn').style.display='none';document.getElementById('formeditpass').style.display='';return false;"
								class="button">Change password</Button>
							<button class="button" type="button" id="deleteprofilebutn"
								onclick="document.getElementById('changepassbutn').style.display='none';document.getElementById('deleteprofilebutn').style.display='none';document.getElementById('deletebutton').style.display='';return false;">Delete
								account</button>

						</div>
					</form>
					<form id="formeditpass" name="formPassChange" method="post"
						action="ControllerServlet" onsubmit="return validatepass()"
						style="display: none; margin-top: 1em">
						<input type="hidden" name="htmlFormName" value="formPassChange" />
						<p id="errorEditPassDisplay" style="color: red"></p>
						<table>
							<tr>
								<td><font size="4"><b>Change Password:&nbsp;</b></font></td>
							</tr>
							<tr>

								<td><input class="tbd" type="password"
									name="editPasswordOld" placeholder="Old Password"></td>
							</tr>
							<tr>

								<td><input class="tbd" type="password"
									name="editPasswordNew" placeholder="New Password"></td>
							</tr>
							<tr>

								<td><input class="tbd" type="password"
									name="editConfirmPasswordNew"
									placeholder="Confirm New Password"></td>
							</tr>
							<tr>
								<td align="right"><input style="text-align: right;"
									type="submit" name="update" class="button" value="Change">
									<input type="button" value="Cancel" id="cancelbuttonpass"
									onclick="document.getElementById('deleteprofilebutn').style.display='';document.getElementById('changepassbutn').style.display='';document.getElementById('formeditpass').style.display='none';return false;"
									class="button"></td>
							</tr>
						</table>
					</form>



					<form id="formdelete" name="formProfileDelete" method="post"
						action="ControllerServlet">
						<input type="hidden" name="htmlFormName" value="formProfileDelete" />
						<table id="deletebutton" style="display: none; margin-top: 1em">

							<tr>
								<td><font color="red" size="4"><b>Warning:</b></font></td>
							</tr>

							<tr>
								<td><font color="red" size="4">This is will
										completely delete your data like tweets, followers, following
										and login credentials.<br> <br>You cannot rollback
										after this step. Are you sure?
								</font> <input class="button" type="submit" name="delete"
									value="Delete">
									<Button
										onclick="document.getElementById('changepassbutn').style.display='';document.getElementById('deleteprofilebutn').style.display='';document.getElementById('deletebutton').style.display='none';return false;"
										class="button">Cancel</Button></td>
							</tr>
						</table>
					</form>
				</fieldset>

				<div id="tweetEditBox" style="display: none; margin-top: 3em">
					<p id="erroredittweet" style="color: red"></p>
					<form name="formEditTweet" method="post" action="ControllerServlet"
						onsubmit="return validateEditTweet()">
						<input type="hidden" name="htmlFormName" value="formEditTweet" />
						<label for="intext"><font size="4"><b>Edit
									Tweet:</b></font></label>
						<textarea onkeyup="textAreaAdjust(this)" style="overflow: hidden"
							id="intext" autofocus="autofocus" rows="4" cols="50"
							maxlength="140" name="tweetEditArea"></textarea>
						<input type="hidden" name="tid" id="tweetid">
						<Button style="float: right;"
							onclick="document.getElementById('tweetEditBox').style.display='none';return false;"
							class="button">Cancel</Button>

						<input style="float: right;" type="submit" name="editTweet"
							class="button" value="Edit">

					</form>

				</div>
				<div style="margin-top: 3em">

					<%
						if (errorEditTweetMsg != null) {
					%>
					<p id="errorTweetDisplay" style="color: red">
						*<%=errorEditTweetMsg%>
					</p>
					<%
						session.removeAttribute("editTweetErrorMsg");
						} else if (successEditTweetMsg != null) {
					%>
					<p id="successTweetDisplay" style="color: green">
						*<%=successEditTweetMsg%>
					</p>
					<%
						session.removeAttribute("editTweetSuccessMsg");
						}
					%>
					<p>
						<font size="5"><b>Tweet Settings:</b></font>
					</p>

					<table class="hovertable">
						<%
							if (vpbAllTweets != null) {
						%>
						<tr>

							<th>Tweet</th>
							<th>Time</th>
							<th colspan="2">Action</th>
						</tr>


						<%
							for (int i = 0; i < vpbAllTweets.size(); i++) {
						%>
						<tr onmouseover="this.style.backgroundColor='#ffffff';"
							onmouseout="this.style.backgroundColor='#c0deed';">


							<td><%=vpbAllTweets.elementAt(i).getMessage()%></td>
							<td><%=vpbAllTweets.elementAt(i).getTimestamp()%></td>
							<td><a class="tweetUserProfile" href=""
								onclick="document.getElementById('tweetEditBox').style.display='';document.getElementById('tweetid').value='<%=vpbAllTweets.elementAt(i).getTweet_id()%>';return false;">
									Edit</a></td>
							<td><a class="tweetUserProfile"
								href="ControllerServlet?input=deletetweet&tweetid=<%=vpbAllTweets.elementAt(i).getTweet_id()%>&act=delete">Delete
							</a></td>
						</tr>

						<%
							}
								session.removeAttribute("CurrentUserTweets");
							} else {
						%>
						<p style="margin-top: 2em">
							<font size="5" color="red">You have no tweets to edit! </font>
						</p>

						<%
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