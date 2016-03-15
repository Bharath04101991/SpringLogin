package com.vlabs.form;

import org.hibernate.validator.constraints.NotEmpty;

public class ForgotPasswordForm {
    @NotEmpty(message="Email Field must not be null")
	private String emailId;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
}
