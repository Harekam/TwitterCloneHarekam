package com.csc.applicationLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;

import com.csc.bean.PersonBean;
import com.csc.connect.ConnectionManager;
import com.csc.dao.PersonDao;

public class Search implements PersonDao {
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// method to search a person by its fullname
	public Vector<PersonBean> getSearchedPerson(PersonBean pb) {
		// TODO Auto-generated method stub
		String nameSearch = pb.getFullName();

		String userid = pb.getUserName();

		Vector<PersonBean> vpb = new Vector<PersonBean>();

		Connection c1 = ConnectionManager.getConnection();
		String strSearchQuery = "select user_id,fullname,email "
				+ "from person where lower(fullname)||upper(fullname) LIKE '%"
				+ nameSearch + "%' and NOT user_id=?";
		try {

			pstmt = c1.prepareStatement(strSearchQuery);
			pstmt.setString(1, userid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PersonBean pbsearch = new PersonBean();
				pbsearch.setFullName(rs.getString("fullname"));
				pbsearch.setUserName(rs.getString("user_id"));
				pbsearch.setEmail(rs.getString("email"));

				vpb.addElement(pbsearch);
			}

		} catch (SQLException e) {

		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) { /* ignored */
				}
			}
			if (c1 != null) {
				try {
					c1.close();
				} catch (SQLException e) { /* ignored */
				}
			}
		}
		return vpb;
	}

	@Override
	public String insertPerson(PersonBean pb) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<String> getPerson(PersonBean pb) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPersonActive(PersonBean pb) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validate(String strValidateQuery, String tovalidate)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFollowPerson(PersonBean pb) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean deletePerson(String userName) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editPerson(PersonBean pb) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editPassPerson(PersonBean pb) {
		// TODO Auto-generated method stub
		return false;
	}

}
