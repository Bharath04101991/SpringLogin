package com.vlabs.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="request")
public class LoginServiceRequest {

private String userName;
private String password;
public String getUserName() {
	return userName;
}
@XmlElement
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
@XmlElement
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "LoginServiceRequest [userName=" + userName + ", password="
			+ password + "]";
}

public LoginServiceRequest() {
    super();
  }

}
