package com.csc.servlet;

import java.io.IOException;
import java.util.Vector;

import com.csc.applicationLogic.Tweet;
import com.csc.bean.PersonBean;
import com.csc.connect.ConnectionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ControllerServlet
 */
public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		// Getting parameters defined in web.xml
		String serverIP = config.getInitParameter("ServerIP");
		String portNumber = config.getInitParameter("PortNumber");
		String sid = config.getInitParameter("Sid");
		String driverName = config.getInitParameter("DriverName");
		String urlPart = config.getInitParameter("UrlPart");
		String uname = config.getInitParameter("Username");
		String pass = config.getInitParameter("Password");
		String url = urlPart + serverIP + ":" + portNumber + ":" + sid;
		/*
		 * Making Anonymous object of ConnectionManager hence calling
		 * constructor and passing values to be assigned.
		 */
		new ConnectionManager(url, driverName, uname, pass);
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	enum FormGet {
		signout, home, profile, following, followers, deletetweet, search
	};

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		/*
		 * on calling this servlet explicitly, it will redirect to Login.jsp
		 */

		HttpSession session = request.getSession();
		String userid = request.getParameter("userid");
		String input = request.getParameter("input");
		String out = request.getParameter("out");

		RequestDispatcher rd;
		if (session.getAttribute("login") == null) {
			if (out == null || out.equals("login")) {

				rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
				System.out.println("Value is null in login");

			} else if (out.equals("signup")) {

				rd = request.getRequestDispatcher("Signup.jsp");
				rd.forward(request, response);
				System.out.println("Value is null in signup");
			}

		} else if (userid != null) {
			rd = request.getRequestDispatcher("UserProfileServlet");
			rd.forward(request, response);
		} else if (input != null) {
			FormGet formget = FormGet.valueOf(input);
			switch (formget) {

			case signout:
				rd = request.getRequestDispatcher("SignoutServlet");
				rd.forward(request, response);
				break;
			case home:
				rd = request.getRequestDispatcher("HomeServlet");
				rd.forward(request, response);
				break;
			case profile:
				PersonBean pbLog = (PersonBean) session.getAttribute("login");
				Vector<PersonBean> ve = new Tweet().showUserTweets(pbLog,
						"current");
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

				rd = request.getRequestDispatcher("Profile.jsp");
				rd.forward(request, response);
				break;
			case following:
				rd = request.getRequestDispatcher("FollowingServlet");
				rd.forward(request, response);
				break;
			case followers:
				rd = request.getRequestDispatcher("FollowersServlet");
				rd.forward(request, response);
				break;
			case deletetweet:
				rd = request.getRequestDispatcher("EditTweetServlet");
				rd.forward(request, response);
				break;
			case search:
				rd = request.getRequestDispatcher("Search.jsp");
				rd.forward(request, response);
				break;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	enum FormPost {
		formsign, formlog, formHomeUpdate, formSearch, formFollow, formProfileUpdate, formProfileDelete, formEditTweet, formPassChange
	};

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.setContentType("text/html");
		// PrintWriter pw = response.getWriter();
		// fetching form's name
		String formName = request.getParameter("htmlFormName");

		/*
		 * check form's name in switch and move control to respected servlet
		 */
		FormPost formpost = FormPost.valueOf(formName);
		RequestDispatcher rd;
		switch (formpost) {
		case formsign:
			rd = request.getRequestDispatcher("SignupServlet");
			rd.forward(request, response);
			break;
		case formlog:
			rd = request.getRequestDispatcher("LoginServlet");
			rd.forward(request, response);
			break;
		case formHomeUpdate:
			rd = request.getRequestDispatcher("TweetServlet");
			rd.forward(request, response);
			break;
		case formSearch:
			rd = request.getRequestDispatcher("SearchServlet");
			rd.forward(request, response);
			break;
		case formFollow:
			rd = request.getRequestDispatcher("FollowServlet");
			rd.forward(request, response);
			break;
		case formProfileUpdate:
			rd = request.getRequestDispatcher("EditProfileServlet");
			rd.forward(request, response);
			break;
		case formProfileDelete:
			rd = request.getRequestDispatcher("DeleteProfileServlet");
			rd.forward(request, response);
			break;
		case formEditTweet:
			rd = request.getRequestDispatcher("EditTweetServlet");
			rd.forward(request, response);
			break;
		case formPassChange:
			rd = request.getRequestDispatcher("EditProfileServlet");
			rd.forward(request, response);
			break;
		}

	}
}
