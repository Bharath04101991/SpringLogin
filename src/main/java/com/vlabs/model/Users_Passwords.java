package com.vlabs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.netflix.governator.annotations.binding.Color;


@Entity
@Table(name="users_password")
public class Users_Passwords implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="p_id")
	private int p_id;
	
	@Column(name="user_password")
	private String password;
	
	@Column(name="user_password2")
	private String password2;
	
	
	@Column(name="user_password3")
	private String password3;
	
	@Column(name="user_password4")
	private String password4;
	
	@Column(name="id")
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getPassword3() {
		return password3;
	}

	public void setPassword3(String password3) {
		this.password3 = password3;
	}

	public String getPassword4() {
		return password4;
	}

	public void setPassword4(String password4) {
		this.password4 = password4;
	}

}
