package com.csc.applicationLogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.csc.bean.PersonBean;
import com.csc.connect.ConnectionManager;
import com.csc.dao.TweetDao;

public class Tweet implements TweetDao {
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// method to insert tweet
	@Override
	public boolean setTweet(PersonBean pb) {
		// TODO Auto-generated method stub
		boolean isTweetSet = false;
		Connection c1 = ConnectionManager.getConnection();
		String message = pb.getMessage();
		String userName = pb.getUserName();
		String strInsertTweet = "insert into tweet(tweet_id,user_id,message) values (seq_tweet_id.nextval,?,?)";
		try {
			pstmt = c1.prepareStatement(strInsertTweet);
			pstmt.setString(1, userName);
			pstmt.setString(2, message);
			pstmt.executeUpdate();
			System.out.println("Message set");
			isTweetSet = true;
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
		return isTweetSet;

	}

	// method to count tweets
	public int countTweets(PersonBean pb, String of) {
		// TODO Auto-generated method stub
		Connection c1 = ConnectionManager.getConnection();
		String userName = null;
		if (of.equals("current")) {
			userName = pb.getUserName();
		} else if (of.equals("follow")) {
			userName = pb.getFollowingId();
		}
		int tweetCount = 0;
		String strCountQuery = "Select count(*) from tweet where user_id=?";
		try {
			pstmt = c1.prepareStatement(strCountQuery);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				tweetCount = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println(e);
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
		return tweetCount;
	}

	// method to fetch tweets of logged in user and user's he follow
	public Vector<PersonBean> showAllTweets(PersonBean pb) {

		Vector<PersonBean> vpb = new Vector<PersonBean>();
		String userid = pb.getUserName();
		Connection c1 = ConnectionManager.getConnection();
		String strDispAllTweetQuery = "select user_id,message,"
				+ "TO_CHAR(created, 'DD-MM-YYYY hh:mi AM') "
				+ "as time from tweet where user_id=? or "
				+ "user_id in(select following_id from "
				+ "following where user_id=?)  " + "ORDER BY created desc";
		try {

			pstmt = c1.prepareStatement(strDispAllTweetQuery);
			pstmt.setString(1, userid);
			pstmt.setString(2, userid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PersonBean pbtweets = new PersonBean();

				pbtweets.setUserName(rs.getString("user_id"));

				pbtweets.setMessage(rs.getString("message"));
				pbtweets.setTimestamp(rs.getString("time"));

				vpb.addElement(pbtweets);
			}

		} catch (SQLException e) {
			System.out.println(e);
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

	// method to show tweets of other users
	@Override
	public Vector<PersonBean> showUserTweets(PersonBean pb, String type) {
		// TODO Auto-generated method stub
		Vector<PersonBean> vpb = new Vector<PersonBean>();
		String userid = null;
		if (type.equals("follow")) {
			userid = pb.getFollowingId();
		} else if (type.equals("current")) {

			userid = pb.getUserName();
		}

		Connection c1 = ConnectionManager.getConnection();
		String strDispUserTweetQuery = "select tweet_id,message,TO_CHAR(created, 'DD-MM-YYYY hh:mi AM') as time from tweet where user_id=? ORDER BY created desc";
		try {

			pstmt = c1.prepareStatement(strDispUserTweetQuery);
			pstmt.setString(1, userid);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				PersonBean pbtweets = new PersonBean();
				pbtweets.setTweet_id(rs.getInt("tweet_id"));
				pbtweets.setMessage(rs.getString("message"));
				pbtweets.setTimestamp(rs.getString("time"));

				vpb.addElement(pbtweets);
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

	// method to delete tweet
	public boolean deleteTweet(PersonBean pb) {

		boolean isTweetDelete = false;
		Connection c1 = ConnectionManager.getConnection();
		int tweetid = pb.getTweet_id();
		String userName = pb.getUserName();
		String strInsertTweet = "delete from tweet where tweet_id=? and user_id=?";
		try {
			pstmt = c1.prepareStatement(strInsertTweet);
			pstmt.setInt(1, tweetid);
			pstmt.setString(2, userName);
			int check = pstmt.executeUpdate();

			if (check != 0) {
				isTweetDelete = true;
				System.out.println("Tweet Deleted");
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
		return isTweetDelete;

	}

	// method to edit tweet
	public boolean isTweetEdited(PersonBean pb) {
		Connection c1 = ConnectionManager.getConnection();
		boolean isEdited = false;
		String msgtweet = pb.getMessage();
		int tweetid = pb.getTweet_id();
		String userid = pb.getUserName();
		String strEditTweetQuery = "update tweet set message=?,created=current_timestamp where tweet_id=? and user_id=?";
		try {

			pstmt = c1.prepareStatement(strEditTweetQuery);

			pstmt.setString(1, msgtweet);
			pstmt.setInt(2, tweetid);

			pstmt.setString(3, userid);
			int check = pstmt.executeUpdate();
			if (check != 0) {
				isEdited = true;
			}

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

		return isEdited;

	}
}
