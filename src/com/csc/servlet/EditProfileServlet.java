package com.csc.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csc.applicationLogic.EditProfile;
import com.csc.applicationLogic.Tweet;
import com.csc.bean.PersonBean;

/**
 * Servlet implementation class DisplayAllTweetsServlet
 */
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProfileServlet() {
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
	enum FormEditProfile {
		formProfileUpdate, formPassChange

	};

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String formName = request.getParameter("htmlFormName");
		boolean isEdit = false;
		HttpSession session = request.getSession();
		// fetch the login details form attribute set at the login time
		PersonBean pbLog = (PersonBean) request.getSession().getAttribute(
				"login");
		String userid = pbLog.getUserName();
		FormEditProfile formeditprofile = FormEditProfile.valueOf(formName);
		String success = null, error = null;
		PersonBean pbEdit = new PersonBean();
		pbEdit.setUserName(userid);
		switch (formeditprofile) {
		case formProfileUpdate:
			String editfullname = request.getParameter("editFullName");
			String editemail = request.getParameter("editEmail");
			pbEdit.setFullName(editfullname);
			pbEdit.setEmail(editemail);

			// pass the obj to EditProfile() which returns the string
			isEdit = new EditProfile().editPerson(pbEdit);
			if (isEdit) {

				success = "Profile Successfully Updated!";
				pbLog.setEmail(editemail);
				// update the fullname
				request.getSession().setAttribute("fullname", editfullname);
			} else {
				error = "The email you entered already exists!";
			}
			break;

		case formPassChange:

			// get the paramters to be edited

			String editpass = request.getParameter("editPasswordNew");
			String editpassold = request.getParameter("editPasswordOld");

			// wrap values in object

			pbEdit.setOldPassword(editpassold);

			pbEdit.setPassword(editpass);
			isEdit = new EditProfile().editPassPerson(pbEdit);
			if (isEdit) {

				success = "Password Successfully Updated!";

			} else {
				error = "Old password you entered does not match our records!";
			}
			break;
		}

		// if returned is true give control to this
		if (isEdit) {

			// set the succes msg in attrbute
			session.setAttribute("successEditMsg", success);

		}

		// if returned is not true give control to this
		else {

			// set the error msg in attribute
			session.setAttribute("errorEditMsg", error);

		}

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