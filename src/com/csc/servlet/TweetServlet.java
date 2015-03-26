package com.csc.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csc.applicationLogic.Tweet;
import com.csc.bean.PersonBean;

/**
 * Servlet implementation class TweetServlet
 */
public class TweetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TweetServlet() {
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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		String tweet = null;
		tweet = request.getParameter("tweetArea");// getting tweet from
													// view(jsp)

		// getting "login" attribute set at login time
		PersonBean pbUserData = (PersonBean) request.getSession().getAttribute(
				"login");

		if ((tweet != null) && tweet != " ") {
			pbUserData.setMessage(tweet);

			// Calling setTweet() from tweet class to insert tweet which returns
			// boolean
			boolean isTweet = new Tweet().setTweet(pbUserData);

			if (isTweet) {
				String msgSuccess = "Tweet Updated Successfully!";

				int tweetCount = new Tweet().countTweets(pbUserData, "current");

				// again set tweet count
				session.setAttribute("CurrentTweetCount", tweetCount);
				session.setAttribute("msgSuccessTweet", msgSuccess);

			} else {
				String msgError = "Sorry tweet cannot be updated, please try again!";
				session.setAttribute("msgErrorTweet", msgError);

			}
		} else {
			String msgError = "Tweet is empty!";
			session.setAttribute("msgErrorTweet", msgError);

		}
		// getting the tweets again and setting the attribute again
		Vector<PersonBean> ve = new Tweet().showAllTweets(pbUserData);
		session.setAttribute("allTweets", ve);
		// fwd req to homepage.jsp
		response.sendRedirect("Redirect");

	}

}
