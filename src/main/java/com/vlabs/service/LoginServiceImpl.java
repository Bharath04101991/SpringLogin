package com.vlabs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vlabs.dao.LoginDAO;
import com.vlabs.model.Users;
import com.vlabs.utility.MailSender;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired
	LoginDAO logindao;
	@Autowired
	MailSender mailSender;

	@Override
	public boolean loginService(String userEmail, String password) {
		
		boolean result;
		result=logindao.checkLoginNew(userEmail, password);
		
		return result;
	}

	@Override
	public boolean forgotPasswordService(String emailId) {
		boolean validEmailId;
		Users user=logindao.validateEmail(emailId);
		if (user != null) {
			mailSender.mailService(user);
			validEmailId = true;
		} else {
			validEmailId = false;
		}
		return validEmailId;
	}

	@Override
	public boolean loginServiceusingMobileNum(String mobileNum, String password) {
		// TODO Auto-generated method stub
		return logindao.checkLoginNew(mobileNum, password);
	}

}
