package com.csc.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csc.applicationLogic.Login;

import com.csc.bean.PersonBean;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		// fetching values of user inputs
		HttpSession session = request.getSession();
		String loginType = request.getParameter("username");
		String pass = request.getParameter("inpassword");
		PersonBean pbLog = new PersonBean();
		Vector<String> vmessage;
		String type = null;

		// checks user entered email or username as logintype

		if (loginType.matches(EMAIL_PATTERN)) {
			type = "email";
			pbLog.setType(type);
			pbLog.setEmail(loginType);

		} else {
			type = "user_id";
			pbLog.setType(type);
			pbLog.setUserName(loginType);

		}
		pbLog.setPassword(pass);
		// calling getPerson method in Login class
		vmessage = new Login().getPerson(pbLog);
		/*
		 * Check if element at '0' index of vector obj(vmessage) is equal to
		 * "false" then print the elements/messages in vmessage(vector obj) else
		 * create a session and go to "HomePage.jsp"
		 */

		if ((vmessage.elementAt(0)).equals("false")) {

			request.setAttribute("errorLogMsg", vmessage.elementAt(1));
			RequestDispatcher rqdis = request.getRequestDispatcher("Login.jsp");
			rqdis.forward(request, response);

		} else if (vmessage.elementAt(0).equals("true")) {

			/*
			 * Setting Active attribute in Person table to '1' of person who
			 * logs in
			 */
			pbLog.setFullName(vmessage.elementAt(1));
			pbLog.setUserName(vmessage.elementAt(2));
			pbLog.setEmail(vmessage.elementAt(3));
			pbLog.setActive(1);
			new Login().setPersonActive(pbLog);
			session.setAttribute("userLogin", "Logged in");

			session.setAttribute("login", pbLog);// putting login details in an
													// attribute

			String fullname = vmessage.elementAt(1);
			session.setAttribute("fullname", fullname);
			// forward control to HomeServlet
			response.sendRedirect("Redirect");
		}
	}
}
