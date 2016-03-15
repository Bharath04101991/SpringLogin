package com.vlabs.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.hateoas.Resource;

/**
 * Created by bharath on 14/11/14.
 */
public class RegistrationForm {


    private int id;
    @NotBlank(message="Username cannot be empty")
    private String userName;
    @NotBlank(message="Password cannot be empty")
    private String password;
    @NotBlank(message="EmailId cannot be empty")
    @Email
    private String emailId;
    @NotBlank(message="Mobile Num cannot be empty")
    private String mobileNum;

    public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
