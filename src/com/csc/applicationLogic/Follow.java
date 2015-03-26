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

public class Follow implements PersonDao {
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// method to make logged in user follow other users
	public void setFollowPerson(PersonBean pb) {

		// getting connection
		Connection c1 = ConnectionManager.getConnection();
		String followingUser = pb.getFollowingId();
		String currentuser = pb.getUserName();

		String strFollowQuery = "insert into following (user_id,following_id) values(?,?)";
		try {
			pstmt = c1.prepareStatement(strFollowQuery);
			pstmt.setString(1, currentuser);
			pstmt.setString(2, followingUser);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	// method to get the fullname of clicked user
	@Override
	public Vector<String> getPerson(PersonBean pb) {
		// TODO Auto-generated method stub

		String userid = pb.getFollowingId();

		Vector<String> vpb = new Vector<String>();
		String fullname;
		Connection c1 = ConnectionManager.getConnection();
		String strSearchQuery = "select fullname from person where user_id=?";
		try {

			pstmt = c1.prepareStatement(strSearchQuery);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {

				fullname = rs.getString("fullname");

				vpb.addElement(fullname);
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

	// method to check if the logged in user is following the clicked user or
	// not
	public Vector<String> isFollowing(PersonBean pb) {

		Connection c1 = ConnectionManager.getConnection();
		String userName = pb.getUserName();

		Vector<String> ve = new Vector<String>();
		String strCountQuery = "Select following_id from following where user_id=?";
		try {
			pstmt = c1.prepareStatement(strCountQuery);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ve.addElement(rs.getString("following_id"));

			}

		} catch (SQLException e) {
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
		return ve;
	}

	// method to unfollow a user
	public void setUnfollowPerson(PersonBean pb) {
		// TODO Auto-generated method stub

		Connection c1 = ConnectionManager.getConnection();
		String followingUser = pb.getFollowingId();
		String currentuser = pb.getUserName();

		String strFollowQuery = "DELETE from following where user_id=? and following_id=?";
		try {
			pstmt = c1.prepareStatement(strFollowQuery);
			pstmt.setString(1, currentuser);
			pstmt.setString(2, followingUser);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	// method to count the following users
	public int countfollowing(PersonBean pb, String of) {
		Connection c1 = ConnectionManager.getConnection();
		String userName = null;
		// method to check if count to be fetched is of current user or the user
		// clicked
		if (of.equals("current")) {
			userName = pb.getUserName();
		} else if (of.equals("follow")) {
			userName = pb.getFollowingId();
		}
		int followingCount = 0;
		String strCountQuery = "Select count(*) from following where user_id=?";
		try {
			pstmt = c1.prepareStatement(strCountQuery);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				followingCount = rs.getInt(1);
			}

		} catch (SQLException e) {
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
		return followingCount;
	}

	// method to count followers
	public int countfollowers(PersonBean pb, String of) {
		Connection c1 = ConnectionManager.getConnection();
		String userName = null;
		// method to check if count to be fetched is of current user or the user
		// clicked
		if (of.equals("current")) {
			userName = pb.getUserName();
		} else if (of.equals("follow")) {
			userName = pb.getFollowingId();
		}
		int followersCount = 0;
		String strCountQuery = "Select count(*) from following where following_id=?";
		try {
			pstmt = c1.prepareStatement(strCountQuery);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				followersCount = rs.getInt(1);
			}

		} catch (SQLException e) {
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
		return followersCount;
	}

	// method to get the following users and their details
	public Vector<PersonBean> getfollowing(PersonBean pb, String of) {
		Vector<PersonBean> vpb = new Vector<PersonBean>();
		Connection c1 = ConnectionManager.getConnection();
		String userName = null;
		if (of.equals("current")) {
			userName = pb.getUserName();
		} else if (of.equals("follow")) {
			userName = pb.getFollowingId();
		}

		String strCountQuery = "select user_id,fullname,email "
				+ "from person where "
				+ "user_id in (select following_id from following where user_id='"
				+ userName + "')";
		try {
			pstmt = c1.prepareStatement(strCountQuery);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				PersonBean pbfollowing = new PersonBean();
				pbfollowing.setFullName(rs.getString("fullname"));
				pbfollowing.setUserName(rs.getString("user_id"));

				pbfollowing.setEmail(rs.getString("email"));

				vpb.addElement(pbfollowing);
			}

		} catch (SQLException e) {
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
		return vpb;

	}

	// method to get the followers details
	public Vector<PersonBean> getfollowers(PersonBean pb, String of) {
		Vector<PersonBean> vpb = new Vector<PersonBean>();
		Connection c1 = ConnectionManager.getConnection();
		String userName = null;
		if (of.equals("current")) {
			userName = pb.getUserName();
		} else if (of.equals("follow")) {
			userName = pb.getFollowingId();
		}

		String strCountQuery = "select user_id,fullname,email "
				+ "from person where "
				+ "user_id in(select user_id from following where following_id='"
				+ userName + "')";
		try {
			pstmt = c1.prepareStatement(strCountQuery);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				PersonBean pbfollowing = new PersonBean();
				pbfollowing.setFullName(rs.getString("fullname"));
				pbfollowing.setUserName(rs.getString("user_id"));

				pbfollowing.setEmail(rs.getString("email"));

				vpb.addElement(pbfollowing);
			}

		} catch (SQLException e) {
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
		return vpb;

	}

	@Override
	public String insertPerson(PersonBean pb) throws SQLException {
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
