package com.csc.applicationLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.csc.bean.PersonBean;
import com.csc.connect.ConnectionManager;
import com.csc.dao.PersonDao;

public class Signup implements PersonDao {
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// method to insert data of person during signup
	public String insertPerson(PersonBean pb) {
		Connection c1 = ConnectionManager.getConnection();
		boolean isUserId, isEmail;
		String message = null;
		String strInsertQuery = "insert into person (user_id,password,fullname,email) values (?,?,?,?)";
		String userValidateQuery = "select user_id from person where user_id=?";
		String emailValidateQuery = "select email from person where email=?";
		String uname = pb.getUserName();
		String pass = pb.getPassword();
		String fullName = pb.getFullName();
		String email = pb.getEmail();
		isUserId = validate(userValidateQuery, uname);
		isEmail = validate(emailValidateQuery, email);
		// checking is userid or email entered already exists or not
		if (!isUserId) {
			message = "Username already exists!";
		} else if (!isEmail) {
			message = "Email already exists!";
		}
		if (isUserId && isEmail) {
			try {

				pstmt = c1.prepareStatement(strInsertQuery);

				pstmt.setString(1, uname);
				pstmt.setString(2, pass);
				pstmt.setString(3, fullName);
				pstmt.setString(4, email);

				pstmt.executeUpdate();

				message = "true";
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
		} else if (!isUserId && !isEmail) {
			message = "Username and email already exists!";
		}
		return message;
	}

	// method to validate userid and email
	public boolean validate(String strValidateQuery, String tovalidate) {
		boolean isValidate = true;
		Connection c1 = ConnectionManager.getConnection();
		try {

			pstmt = c1.prepareStatement(strValidateQuery);
			pstmt.setString(1, tovalidate);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				isValidate = false;

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
		return isValidate;

	}

	@Override
	public Vector<String> getPerson(PersonBean pb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPersonActive(PersonBean pb) {
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
