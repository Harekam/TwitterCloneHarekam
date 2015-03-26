package com.csc.dao;

import java.sql.SQLException;
import java.util.Vector;

import com.csc.bean.PersonBean;

public interface PersonDao {

	public String insertPerson(PersonBean pb) throws SQLException;

	public Vector<String> getPerson(PersonBean pb) throws SQLException;

	public void setPersonActive(PersonBean pb) throws SQLException;

	public boolean validate(String strValidateQuery, String tovalidate)
			throws SQLException;

	public Vector<PersonBean> getSearchedPerson(PersonBean pb)
			throws SQLException;

	public void setFollowPerson(PersonBean pb) throws SQLException;

	public boolean deletePerson(String userName) throws SQLException;

	public boolean editPerson(PersonBean pb);

	public boolean editPassPerson(PersonBean pb);
}
