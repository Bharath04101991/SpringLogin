package com.vlabs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class Status {
 
	 private String message;
	 
	 private String result_code;

	public String getMessage() {
		return message;
	}

	@XmlElement
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Status(String message,String result_code) {
	    
	    this.message = message;
	    this.result_code=result_code;
	  }

	  public Status() {
	    super();
	  }

	public String getResult_code() {
		return result_code;
	}
	@XmlElement
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
}
