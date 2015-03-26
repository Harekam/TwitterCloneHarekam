package com.csc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csc.applicationLogic.Follow;

import com.csc.bean.PersonBean;

/**
 * Servlet implementation class FollowServlet
 */
public class FollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FollowServlet() {
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

		// getting userid of person on who current user clicked
		String userid = (String) session.getAttribute("clickeduserid");

		// getting the "login" attribute again which is set at login time
		PersonBean pbLog = (PersonBean) session.getAttribute("login");
		String currentUser = pbLog.getUserName();
		String clickfunf = null;
		clickfunf = request.getParameter("funf");// check the value in "funf"
													// button
		PersonBean pbFollow = new PersonBean();
		pbFollow.setFollowingId(userid);
		pbFollow.setUserName(currentUser);

		// if value of button is "follow" give control to this
		if (clickfunf.equals("Follow")) {

			// logged in user will follow the clicked user id
			// setFollowPerson() is called
			new Follow().setFollowPerson(pbFollow);

		}

		// control to this if value of button is "unfollow"
		else if (clickfunf.equals("Unfollow")) {
			new Follow().setUnfollowPerson(pbFollow);

		}

		// move control to homeservlet
		response.sendRedirect("Redirect");

	}
}
