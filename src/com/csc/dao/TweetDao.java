package com.csc.dao;

import java.sql.SQLException;
import java.util.Vector;

import com.csc.bean.PersonBean;

public interface TweetDao {
public boolean setTweet(PersonBean pb) throws SQLException;
public int countTweets(PersonBean pb,String of) throws SQLException;
public Vector<PersonBean> showAllTweets(PersonBean pb) throws SQLException  ;
public Vector<PersonBean> showUserTweets(PersonBean pb,String type) throws SQLException  ;
}
