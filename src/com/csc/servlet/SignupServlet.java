package com.csc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csc.applicationLogic.Signup;
import com.csc.bean.PersonBean;

/**
 * Servlet implementation class SignupServlet
 */
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignupServlet() {
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
		String suname = request.getParameter("susername");
		String spass = request.getParameter("spassword");
		String sfullname = request.getParameter("fullname");
		String semail = request.getParameter("semail");

		// using UserBean setter getters and wrapping data in object
		PersonBean pbSign = new PersonBean();
		pbSign.setUserName(suname);
		pbSign.setPassword(spass);
		pbSign.setFullName(sfullname);
		pbSign.setEmail(semail);

		/*
		 * call class Signup's method insertPerson
		 */
		Signup sp = new Signup();
		String msgSign = sp.insertPerson(pbSign);

		/*
		 * Check if element at vector obj(msgSign) is equal to "true" then
		 * redirect to "Login.jsp" else print the message
		 */

		if (msgSign.equals("true")) {
			String success = "Successfully signed up, please login to continue!";
			request.setAttribute("successMsg", success);// set attribute on
														// sucessful login

			//forward control to Login.jsp
			RequestDispatcher rqdis = request.getRequestDispatcher("Login.jsp");
			rqdis.forward(request, response);

		} else {

			request.setAttribute("errorSignMsg", msgSign);//setting attribute for error msg
			//forward control to Signup.jsp
			RequestDispatcher rqdis = request
					.getRequestDispatcher("Signup.jsp");
			rqdis.forward(request, response);

		}

	}

}
