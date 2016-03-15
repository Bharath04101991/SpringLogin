package com.vlabs.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name="response")
public class Greeting extends ResourceSupport {
	
	private  String content;
	
	private Users user;
	
	/*@JsonCreator
	public Greeting(@JsonProperty("content") String content){
		this.content=content;
	}*/

	public Greeting(){
		
	}
	
	
	
	public Greeting(String content) {
		super();
		this.content = content;
	}
	
	public Greeting(Users user) {
		super();
		this.user = user;
	}


	public String getContent() {
		return content;
	}

	@XmlElement
	public void setContent(String content) {
		this.content = content;
	}

	
	
	

}
