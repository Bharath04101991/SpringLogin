package com.vlabs.form;

import org.hibernate.validator.constraints.NotEmpty;

public class UsersForm {
	
	@NotEmpty(message="UserNamemust not be blank")
	//@Size(min=5,max=10)
	private String userEmail;
	@NotEmpty(message="must not be blank")
	//@Size(min=8)
	private String password;
	
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
