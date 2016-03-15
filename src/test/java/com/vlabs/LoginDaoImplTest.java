package com.vlabs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.vlabs.dao.LoginDAO;
import com.vlabs.model.Users;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebAppConfig.class })
public class LoginDaoImplTest {

	@Autowired
	LoginDAO logindao;
	
	Users user;

	private static final String userName = "bharathsai";
	private static final String password = "bharath";
	private static final String wrongPassword = "bharat";
	private static final String validEmail = "bharathsai007@gmail.com";

	@Test
	public void checkLoginTest() {
		boolean result = logindao.checkLogin(userName, password);
		if (result)
			System.out.println("UserFound");
		else
			System.out.println("Not Found");
	}

	@Test
	public void checkLogin_failureTest() {
		boolean result = logindao.checkLogin(userName, wrongPassword);
		if (result)
			System.out.println("UserFound");
		else
			System.out.println("Not Found");
	}

	@Test
	public void validateEmailTest(){
		Users user=logindao.validateEmail(validEmail);
		if (user!=null)
			System.out.println(validEmail+" is a valid mail");
		else
			System.out.println("Invalid Email");
		
	}
	
	
	@Test
	public void addUserTest(){
		user=createUsersBean();
		boolean result=logindao.addEmployee(user);
		if(result)
			System.out.println("User is created");
		else
			System.out.println("please try again");
	}
	
	 public Users createUsersBean(){
		  Users user=new Users();
		  user.setUserName("gsdad");
		  user.setPassword("dsgsdd");
		  user.setMobileNum("654654645");
	      user.setEmailId("bhabsjkjk@kjdsakj.com");
	      return user;
	 }
	 
	 
	
	
}
