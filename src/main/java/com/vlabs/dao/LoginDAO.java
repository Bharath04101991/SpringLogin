package com.vlabs.dao;

import com.vlabs.model.Users;
import com.vlabs.model.Users_Passwords;

import java.util.List;

public interface LoginDAO {
	public boolean checkLogin(String userName, String password);
	
	public boolean checkLoginUsingMobileNum(String mobileNum, String password);

	public Users validateEmail(String emailId);

	public boolean addEmployee(Users user);

	public List<Users> getList();

	public Users getUserById(int id);

	public boolean update(Users user);

	public boolean delete(Users user);

	public boolean checkLoginNew(String userEmail, String password);

	public Users getUserByEmail(String emailId);

	public Users_Passwords getPasswordsById(int id);

}
