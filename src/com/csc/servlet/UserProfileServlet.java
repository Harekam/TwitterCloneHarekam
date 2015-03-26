package com.csc.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csc.applicationLogic.Follow;
import com.csc.applicationLogic.Tweet;
import com.csc.bean.PersonBean;

/**
 * Servlet implementation class UserProfileServlet
 */
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		// get the clicked user id
		String clickeduserid = request.getParameter("userid");

		// set the clicked user id in attribute
		session.setAttribute("clickeduserid", clickeduserid);

		// get the login details from login attribute set at login time
		PersonBean pbLog = (PersonBean) session.getAttribute("login");
		String currentUser = pbLog.getUserName();

		// checking if logged in user clicked on its user id

		// if yes move control to profile.jsp
		if (clickeduserid.equals(currentUser)) {

			Vector<PersonBean> ve = new Tweet()
					.showUserTweets(pbLog, "current");
			session.setAttribute("CurrentUserTweets", ve);
			RequestDispatcher rqdis = request
					.getRequestDispatcher("Profile.jsp");
			rqdis.forward(request, response);
		}

		// if no move control to profile of user clicked
		else {

			PersonBean pbFollow = new PersonBean();
			pbFollow.setUserName(currentUser);
			pbFollow.setFollowingId(clickeduserid);
			Vector<String> vef = new Follow().getPerson(pbFollow);// to get
																	// fullname
																	// of
																	// clicked
																	// user

			pbFollow.setFullName(vef.elementAt(0));

			// count the tweets,following,follower count of clicked user
			int tweetCount = new Tweet().countTweets(pbFollow, "follow");

			int followingcount = new Follow()
					.countfollowing(pbFollow, "follow");
			int followerCount = new Follow().countfollowers(pbFollow, "follow");

			// set the counts in attribute
			session.setAttribute("ClickedTweetCount", tweetCount);
			session.setAttribute("ClickedFollowingCount", followingcount);
			session.setAttribute("ClickedFollowerCount", followerCount);

			// get the clicked user tweets
			Vector<PersonBean> ve = new Tweet().showUserTweets(pbFollow,
					"follow");
			for (int i = 0; i < ve.size();) {
				if ((ve.elementAt(i).getMessage() == null)
						|| (ve.elementAt(i).getMessage() == "")) {
					break;

				} else {
					// setting these tweets in an attribute
					session.setAttribute("followUserTweets", ve);
					break;
				}
			}

			boolean isfollowing = false;
			// toCheck is current user following this selected user
			Vector<String> veisfollowing = new Follow().isFollowing(pbFollow);
			for (int i = 0; i < veisfollowing.size(); i++) {
				if (veisfollowing.elementAt(i).equals(clickeduserid)) {

					isfollowing = true;
					break;
				}
			}

			pbFollow.setIsFollowing(isfollowing);

			// if isfollowing is true
			if (isfollowing) {

				// set the attribute
				session.setAttribute("followUserProfile", pbFollow);
			}

			// is isfollowing is false
			else {
				session.setAttribute("followUserProfile", pbFollow);
			}

			// move control to followpersonprofile.jsp
			RequestDispatcher rqdis = request
					.getRequestDispatcher("FollowPersonProfile.jsp");
			rqdis.forward(request, response);

		}
	}

}
