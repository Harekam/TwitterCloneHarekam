package com.csc.applicationLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.csc.bean.PersonBean;
import com.csc.connect.ConnectionManager;
import com.csc.dao.PersonDao;

//implementing business logic from PersonDao

public class EditProfile implements PersonDao {
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// method to edit person profile
	public boolean editPerson(PersonBean pb) {
		Connection c1 = ConnectionManager.getConnection();
		boolean isEmail = true;

		String strEditQuery = "update person set fullname=?,email=? where user_id=?";

		String emailValidateQuery = "select email from person where email in(select email from person where NOT user_id=?)";

		String userid = pb.getUserName();

		String fullName = pb.getFullName();
		String email = pb.getEmail();

		// calling method and getting returned values to check if email already
		// exists or not
		Vector<String> vecisEmail = validateEmail(emailValidateQuery, userid);
		for (int i = 0; i < vecisEmail.size(); i++) {
			if (vecisEmail.elementAt(i).equals(email)) {
				isEmail = false;// if email is their in records
				break;
			}
		}
		if (isEmail) {
			try {

				pstmt = c1.prepareStatement(strEditQuery);

				pstmt.setString(1, fullName);
				pstmt.setString(2, email);
				pstmt.setString(3, userid);
				pstmt.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				e.printStackTrace();
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
		}
		return isEmail;

	}

	// method to edit password
	public boolean editPassPerson(PersonBean pb) {
		Connection c1 = ConnectionManager.getConnection();
		boolean ispass = false;
		String strEditPassQuery = "update person set password=? where user_id=?";
		String passValidateQuery = "select password from person where user_id=?";
		String userid = pb.getUserName();
		String passnew = pb.getPassword();
		String passold = pb.getOldPassword();

		// calling method to check if old password entered and pswd stored in db
		// are same or not
		String passfetch = validatePass(passValidateQuery, userid);
		if (passfetch.equals(passold)) {
			ispass = true;// if pswds match
		}
		if (ispass) {
			try {

				pstmt = c1.prepareStatement(strEditPassQuery);

				pstmt.setString(1, passnew);

				pstmt.setString(2, userid);
				pstmt.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				e.printStackTrace();
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

		}

		return ispass;
	}



	// method to validate password
	public String validatePass(String strValidateQuery, String tovalidate) {
		String passfetched = "false";
		Connection c1 = ConnectionManager.getConnection();
		try {

			pstmt = c1.prepareStatement(strValidateQuery);
			pstmt.setString(1, tovalidate);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				passfetched = rs.getString("password");

			}

		} catch (SQLException e) {
			System.out.println("sql error " + e);

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
		return passfetched;

	}

	// method to validate email
	public Vector<String> validateEmail(String strValidateQuery,
			String tovalidate) {
		Vector<String> ve = new Vector<String>();
		Connection c1 = ConnectionManager.getConnection();
		try {

			pstmt = c1.prepareStatement(strValidateQuery);
			pstmt.setString(1, tovalidate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ve.addElement(rs.getString("email"));

			}

		} catch (SQLException e) {
			System.out.println("sql error " + e);

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
		return ve;

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
	public boolean validate(String strValidateQuery, String tovalidate)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}
