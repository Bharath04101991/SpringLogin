package com.vlabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vlabs.model.Status;
import com.vlabs.rest.LoginServiceRequest;
import com.vlabs.service.LoginService;
import com.vlabs.service.RegistrationService;

@RestController
public class RestLoginController {
	@Autowired
	LoginService loginService;
	@Autowired
	RegistrationService registrationService;
	
	//private Status status;
	public RestLoginController(LoginService loginService,
			RegistrationService registrationService) {
		this.loginService = loginService;
		this.registrationService = registrationService;
	}
	
	public RestLoginController() {
		
	}
	
	@RequestMapping(value="rest_getpassword",method=RequestMethod.POST)
	public Status getPassword(@RequestBody String mailId){
		Status status =new Status();
	    
		boolean result=loginService.forgotPasswordService(mailId);
		//String res;
		System.out.println(mailId);
		if(result){
		//res="credentails sent";
		status.setMessage("Credentials are sent to"+mailId);
		}
		else{
		//res="Email Entered is incorrect";
		status.setMessage("Email Entered is incorrect");
		}
		return status;
		
	}
	
	@RequestMapping(value="/rest_delete", method=RequestMethod.DELETE)
	public Status delete(@RequestParam int id)
	{   
        //JsonObject jsonObject=new JsonObject();
		Status status=new Status();
		boolean result=registrationService.delete(id);
		//String res;
		if(result){
			//res="User Deleted";
			//jsonObject.setMessage("User Deleted");
			status.setMessage("User Deleted");
			status.setResult_code("1");
			}
			else{
			//res="Invalid Email Id";
				//jsonObject.setMessage("Invalid Email Id");
				status.setMessage("Invalid Email Id");
				status.setResult_code("0");
			}
		return status;
	}
	
	

	@RequestMapping(value="rest_login",method=RequestMethod.POST)
	public Status login(@RequestBody LoginServiceRequest loginServiceRequest){
		Status status =new Status();
		boolean result=loginService.loginService(loginServiceRequest.getUserName(), loginServiceRequest.getPassword());
		if(result){
			//res="credentails sent";
			status.setMessage("UserLoggedInSuccessfully");
			status.setResult_code("1");
			}
			else{
			//res="Email Entered is incorrect";
			status.setMessage("Credentials Entered is incorrect");
			status.setResult_code("0");
			}
			return status;
		}
	
	@RequestMapping(value="rest_logout",method=RequestMethod.POST)
	public Status logout(@RequestBody LoginServiceRequest loginServiceRequest){
		Status status =new Status();
		boolean result=loginService.loginService(loginServiceRequest.getUserName(), loginServiceRequest.getPassword());
		if(result){
			//res="credentails sent";
			status.setMessage("UserLoggedInSuccessfully");
			status.setResult_code("1");
			}
			else{
			//res="Email Entered is incorrect";
			status.setMessage("Credentials Entered is incorrect");
			status.setResult_code("0");
			}
			return status;
		}
	
	
}
