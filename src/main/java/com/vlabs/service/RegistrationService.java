package com.vlabs.service;

import java.util.List;

import com.vlabs.form.RegistrationForm;
import com.vlabs.model.Users;
import com.vlabs.model.Users_Passwords;

/**
 * Created by bharath on 14/11/14.
 */

public interface RegistrationService {
    public boolean addEmployee(RegistrationForm registrationForm);

    public List<Users> getList();

    public Users getUserById(int id);
    
    public Users_Passwords getPasswordsById(int id);


    public RegistrationForm editService(Users user);

    public boolean update(RegistrationForm registrationForm);

    public boolean delete(RegistrationForm registrationForm);
    
    public boolean delete(int id);

	public Users getUserByEmail(String emailId);
}
