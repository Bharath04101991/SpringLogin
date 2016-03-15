package com.vlabs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Users implements Serializable {
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
	private int id;
    
    @Column(name="user_name")
    private String userName;
    
    @Column(name="user_password")
    private String password;
    
    @Column(name="user_email")
    private String emailId;
    
    @Column(name="user_mobile")
    private String mobileNum;
    
	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	
	@Override
	public String toString() {
		return "Users [id=" + id + ", userName=" + userName + ", password="
				+ password + ", emailId=" + emailId + ", mobileNum="
				+ mobileNum + "]";
	}
}