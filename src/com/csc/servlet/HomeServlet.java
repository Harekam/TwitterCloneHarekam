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
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
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

		// getting "login" attribute set at login time
		PersonBean pbLog = (PersonBean) session.getAttribute("login");

		// Counting the following,followers,tweet count of user who logged in
		int followingcount = new Follow().countfollowing(pbLog, "current");
		int followerCount = new Follow().countfollowers(pbLog, "current");
		int tweetCount = new Tweet().countTweets(pbLog, "current");

		// setting these counts in attribute
		session.setAttribute("CurrentTweetCount", tweetCount);
		session.setAttribute("CurrentFollowingCount", followingcount);
		session.setAttribute("CurrentFollowerCount", followerCount);

		// calling showAllTweets() in Tweet class to get all tweets
		Vector<PersonBean> ve = new Tweet().showAllTweets(pbLog);
		for (int i = 0; i < ve.size();) {
			if ((ve.elementAt(i).getMessage() == null)
					|| (ve.elementAt(i).getMessage() == "")) {
				break;

			} else {
				// setting these tweets in an attribute
				session.setAttribute("allTweets", ve);
				break;
			}
		}
		// forward control to homepage.jsp
		RequestDispatcher rd = request.getRequestDispatcher("HomePage.jsp");
		rd.forward(request, response);
	}

}