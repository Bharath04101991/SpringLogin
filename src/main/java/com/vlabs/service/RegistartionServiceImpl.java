package com.vlabs.service;

import com.vlabs.dao.LoginDAO;
import com.vlabs.form.RegistrationForm;
import com.vlabs.model.Users;
import com.vlabs.model.Users_Passwords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bharath on 14/11/14.
 */
@Service("registration")
public class RegistartionServiceImpl implements RegistrationService {

    @Autowired
    LoginDAO logindao;

    @Override
    public boolean addEmployee(RegistrationForm registrationForm){
        Users user=new Users();
        user.setUserName(registrationForm.getUserName());
        user.setPassword(registrationForm.getPassword());
        user.setEmailId(registrationForm.getEmailId());
        user.setMobileNum(registrationForm.getMobileNum());
        logindao.addEmployee(user);
        return true;
    }

    @Override
    public List<Users> getList() {

        return logindao.getList();
    }

    @Override
    public Users getUserById(int id){
        System.out.println("-------------in reg service");
        Users user=new Users();
        user=logindao.getUserById(id);
        return user;
    }

    @Override
    public RegistrationForm editService(Users user) {
        RegistrationForm registrationForm=createRegistratioFormBean(user);
        return registrationForm;
    }

    @Override
    public boolean update(RegistrationForm registrationForm){
         Users user=createUsersBean(registrationForm);
        logindao.update(user);
        return  true;
    }

    public Users createUsersBean(RegistrationForm registrationForm){
        Users user=new Users();
        user.setId(registrationForm.getId());
        user.setUserName(registrationForm.getUserName());
        user.setPassword(registrationForm.getPassword());
        user.setEmailId(registrationForm.getEmailId());
        user.setMobileNum(registrationForm.getMobileNum());
        return user;
    }
    
    public RegistrationForm createRegistratioFormBean(Users user)
    {
    	RegistrationForm registrationForm=new RegistrationForm();
    	registrationForm.setId(user.getId());
        registrationForm.setUserName(user.getUserName());
        registrationForm.setPassword(user.getPassword());
        registrationForm.setEmailId(user.getEmailId());
        registrationForm.setMobileNum(user.getMobileNum());
        return registrationForm;
    }

    @Override
    public boolean delete(RegistrationForm registrationForm){
        Users user=  createUsersBean(registrationForm);
        logindao.delete(user);
        return true;

    }

	@Override
	public boolean delete(int id) {
		Users user=new Users();
		user=getUserById(id);
		try{
		logindao.delete(user);
		return true;
		}
		catch(Exception e){
		System.out.println();
		return false;
		}
	}

	@Override
	public Users getUserByEmail(String emailId) {
		// TODO Auto-generated method stub
		Users user=logindao.getUserByEmail(emailId);
		return user;
	}

	@Override
	public Users_Passwords getPasswordsById(int id) {
		// TODO Auto-generated method stub
		return logindao.getPasswordsById(id);
	}
}
