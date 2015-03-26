package com.csc.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csc.applicationLogic.Search;
import com.csc.bean.PersonBean;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
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
		String nameSearch = request.getParameter("search");

		// getting attribute "login" which is set at login time
		PersonBean pbLog = (PersonBean) session.getAttribute("login");

		String userid = pbLog.getUserName();
		PersonBean pb = new PersonBean();
		pb.setFullName(nameSearch);
		pb.setUserName(userid);

		// calling getsearchedPerson() in search class
		Vector<PersonBean> ve = new Search().getSearchedPerson(pb);

		// set the result from above in attribute

		for (int i = 0; i < ve.size();) {
			if ((ve.elementAt(i).getFullName() == null)
					|| (ve.elementAt(i).getFullName() == "")) {
				break;

			} else {
				session.setAttribute("searchedResult", ve);
				break;
			}
			/*
			 * System.out.println(ve.elementAt(i).getFullName());
			 * System.out.println(ve.elementAt(i).getUserName());
			 * System.out.println(ve.elementAt(i).getEmail());
			 */

		}

		// fwd result to search.jsp
		response.sendRedirect("RedirectSearch");

	}

}
