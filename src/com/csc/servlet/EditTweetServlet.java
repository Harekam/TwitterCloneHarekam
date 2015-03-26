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
 * Servlet implementation class LoginServlet
 */
public class EditTweetServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditTweetServlet() {
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
		HttpSession session = request.getSession();
		int tweetId = 0;
		PersonBean pbLog = (PersonBean) session.getAttribute("login");
		String action = null;
		action = request.getParameter("act");
		tweetId = Integer.parseInt(request.getParameter("tweetid"));
		if ((tweetId != 0) && (action != null)) {

			String username = pbLog.getUserName();
			PersonBean pbProfile = new PersonBean();
			pbProfile.setTweet_id(tweetId);// set tweetid
			pbProfile.setUserName(username);
			if (action.equals("delete")) {
				boolean isTweetDelete = new Tweet().deleteTweet(pbProfile);
				if (isTweetDelete) {
					String editTweetSuccessMsg = "Tweet Deleted Successfully!";
					session.setAttribute("editTweetSuccessMsg",
							editTweetSuccessMsg);
				} else {
					String editTweetErrorMsg = "Tweet Cannot be deleted! Please try again";
					session.setAttribute("editTweetErrorMsg", editTweetErrorMsg);
				}
			}
		}
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
		int tweetid = 0;
		PersonBean pbLog = (PersonBean) session.getAttribute("login");
		String tweetmsg = null, username = null;
		username = pbLog.getUserName();

		tweetmsg = request.getParameter("tweetEditArea");
		if (tweetmsg != null) {
			tweetid = Integer.parseInt(request.getParameter("tid"));
			PersonBean pbEditTweet = new PersonBean();
			pbEditTweet.setUserName(username);
			pbEditTweet.setMessage(tweetmsg);
			pbEditTweet.setTweet_id(tweetid);
			boolean is_edited = new Tweet().isTweetEdited(pbEditTweet);
			if (is_edited) {
				String editTweetSuccessMsg = "Tweet Edited Successfully!";
				session.setAttribute("editTweetSuccessMsg", editTweetSuccessMsg);
			} else {
				String editTweetErrorMsg = "Tweet Cannot be edited! Please try again";
				session.setAttribute("editTweetErrorMsg", editTweetErrorMsg);
			}
		}

		int tweetCount = new Tweet().countTweets(pbLog, "current");
		session.setAttribute("CurrentTweetCount", tweetCount);
		Vector<PersonBean> ve = new Tweet().showUserTweets(pbLog, "current");
		for (int i = 0; i < ve.size();) {
			if ((ve.elementAt(i).getMessage() == null)
					|| (ve.elementAt(i).getMessage() == "")) {
				break;

			} else {
				// setting these tweets in an attribute
				session.setAttribute("CurrentUserTweets", ve);
				break;
			}
		}

		response.sendRedirect("RedirectProfile");
	}
}
