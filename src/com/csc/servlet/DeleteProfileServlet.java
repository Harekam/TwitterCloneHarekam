package com.csc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csc.applicationLogic.DeleteProfile;

import com.csc.bean.PersonBean;

/**
 * Servlet implementation class DisplayAllTweetsServlet
 */
public class DeleteProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteProfileServlet() {
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
		
		
		//get the details from login attribute set at login time
		PersonBean pbLog = (PersonBean) request.getSession().getAttribute(
				"login");
		String userid = pbLog.getUserName();

		
		//call DeleteProfile() which return boolean
		boolean isdeleted = new DeleteProfile().deletePerson(userid);
		
		//if return true
		if (isdeleted) {
			
			//set successmsg in attribute
			String message = "Profile Successfully Deleted!";
			request.setAttribute("successMsg", message);
			
			//and invalidate the session()
			request.getSession().invalidate();
			
			//fwd control to login.jsp
			RequestDispatcher rq = request.getRequestDispatcher("Login.jsp");
			rq.forward(request, response);
		} 
		
		//if returned false
		else {
			
			//setting the error msg in attribute
			String message = "Profile Cannot be Deleted!";
			request.setAttribute("delete", message);
			
			//move control to profile.jsp
			RequestDispatcher rq = request.getRequestDispatcher("Profile.jsp");
			rq.forward(request, response);
		}
	}
}
