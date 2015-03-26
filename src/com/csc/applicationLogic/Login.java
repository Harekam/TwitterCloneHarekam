package com.csc.applicationLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.csc.bean.PersonBean;
import com.csc.connect.ConnectionManager;
import com.csc.dao.PersonDao;

public class Login implements PersonDao {
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// method to get user details
	public Vector<String> getPerson(PersonBean pb) {
		String type = pb.getType();
		Connection c1 = ConnectionManager.getConnection();
		String logType = null, logPass;
		String typeName = null;
		String strSelectQuery = "select user_id,password,fullname,email from person where "
				+ type + "=? ";
		if (type.equals("email")) {
			typeName = "email";
			logType = pb.getEmail();
		} else if (type.equals("user_id")) {
			typeName = "username";
			logType = pb.getUserName();
		}
		logPass = pb.getPassword();
		Vector<String> message = new Vector<String>();
		try {

			pstmt = c1.prepareStatement(strSelectQuery);

			pstmt.setString(1, logType);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String tempusername = rs.getString("user_id");
				String temppass = rs.getString("password");
				String tempfullname = rs.getString("fullname");
				String tempemail = rs.getString("email");

				if (((logType.equals(tempusername)) || (logType
						.equals(tempemail))) && logPass.equals(temppass)) {
					message.addElement("true");
					message.addElement(tempfullname);
					message.addElement(tempusername);
					message.addElement(tempemail);
				} else {
					message.addElement("false");
					message.addElement("The " + typeName + " and password you "
							+ "entered did not match our records. "
							+ "Please double-check and try again.");

				}

			} else {
				message.addElement("false");
				message.addElement("User does not exists!");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return message;
	}

	// method to set the person active
	public void setPersonActive(PersonBean pb) {
		Connection c1 = ConnectionManager.getConnection();

		int status = pb.getActive();
		String strActive = "update person set active=? where user_id=?";
		String userId = pb.getUserName();

		try {
			c1.setAutoCommit(true);
			pstmt = c1.prepareStatement(strActive);
			pstmt.setInt(1, status);
			pstmt.setString(2, userId);

			pstmt.executeUpdate();
			System.out.println("User Active value is: " + status);

		} catch (SQLException e) {
			System.out.println("sql error active " + e);
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

	}

	@Override
	public String insertPerson(PersonBean pb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validate(String strValidateQuery, String tovalidate) {
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
