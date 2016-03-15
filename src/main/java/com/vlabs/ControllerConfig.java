package com.vlabs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vlabs.controller.LoginController;
import com.vlabs.controller.RabbitMQController;
import com.vlabs.controller.RestLoginController;
import com.vlabs.dao.LoginDAO;
import com.vlabs.dao.LoginDAOImpl;
import com.vlabs.service.LoginService;
import com.vlabs.service.LoginServiceImpl;
import com.vlabs.service.RegistartionServiceImpl;
import com.vlabs.service.RegistrationService;
import com.vlabs.utility.MailSender;
@Configuration
public class ControllerConfig {

	/*@Bean
	public LoginController loginController(){
		return new LoginController();
	}
	
	@Bean
	public RestLoginController restLoginController(){
		return new RestLoginController();
	}
	
	@Bean
	  public RabbitMQController getRabbitMQController(){
	    return new RabbitMQController();
	  }
	@Bean
	public LoginService loginService(){
		return new LoginServiceImpl();
	}
	
	@Bean
	public RegistrationService registrationService(){
		return new RegistartionServiceImpl();
	}
	
	@Bean
	public LoginDAO loginDAO(){
		return new LoginDAOImpl();
	}
	
	@Bean
	public MailSender getMailSender(){
		return new MailSender();
	}*/
}
