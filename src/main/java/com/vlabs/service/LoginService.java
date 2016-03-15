package com.vlabs.service;

public interface LoginService {

	public boolean loginService(String userName,String password);
	
	public boolean forgotPasswordService(String emailId);
	
	public boolean loginServiceusingMobileNum(String mobileNum,String password);
}
