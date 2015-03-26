package com.csc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csc.applicationLogic.Login;
import com.csc.bean.PersonBean;

/**
 * Servlet implementation class SignoutServlet
 */
public class SignoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignoutServlet() {
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
		PersonBean pbZero = new PersonBean();

		// get login details from login attribute set at login time
		HttpSession session = request.getSession();
		PersonBean pb = (PersonBean) session.getAttribute("login");

		// make active zero
		pbZero.setActive(0);
		String userLogName;

		userLogName = pb.getUserName();
		pbZero.setUserName(userLogName);
		pbZero.setType("user_id");

		// pass the wrapped values in obj and send to setPersonactive()
		new Login().setPersonActive(pbZero);
		System.out.println("active is 0 " + userLogName);
		String message = "Successfully Logged out";
		request.setAttribute("successMsg", message);

		session.removeAttribute("login");

		// invalidate session
		session.invalidate();
		RequestDispatcher rq = request.getRequestDispatcher("Login.jsp");
		rq.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
