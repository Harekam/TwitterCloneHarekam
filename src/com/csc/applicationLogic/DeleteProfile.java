package com.csc.applicationLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.Vector;

import com.csc.bean.PersonBean;
import com.csc.connect.ConnectionManager;
import com.csc.dao.PersonDao;

//implementing business logic from PersonDao

public class DeleteProfile implements PersonDao {
	private PreparedStatement pstmt = null;

	// function to delete Person profile
	public boolean deletePerson(String userName) {
		Boolean isDelete = false;

		// getting connection from ConectionManager Class
		Connection c1 = ConnectionManager.getConnection();

		String strDeleteQuery = "delete from person where user_id=?";
		try {
			pstmt = c1.prepareStatement(strDeleteQuery);
			pstmt.setString(1, userName);

			int del = pstmt.executeUpdate();
			if (del != 0) {
				isDelete = true;
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {

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

		return isDelete;

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
	public Vector<PersonBean> getSearchedPerson(PersonBean pb)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFollowPerson(PersonBean pb) throws SQLException {
		// TODO Auto-generated method stub

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
