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
import com.csc.bean.PersonBean;

/**
 * Servlet implementation class FollowersServlet
 */
public class FollowersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		// getting attribute "login" which is set at login time
		PersonBean pbLog = (PersonBean) session.getAttribute("login");
		Vector<PersonBean> ve = new Follow().getfollowers(pbLog, "current");

		for (int i = 0; i < ve.size();) {
			if ((ve.elementAt(i).getFullName() == null)
					|| (ve.elementAt(i).getFullName() == "")) {
				break;

			} else {
				session.setAttribute("followersOfCurrent", ve);
				break;
			}

		}
		RequestDispatcher rqdis = request.getRequestDispatcher("Followers.jsp");
		rqdis.forward(request, response);
	}

}
