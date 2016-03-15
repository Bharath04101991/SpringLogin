package com.vlabs.utility;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import com.vlabs.model.Users;


@Component
public class MailSender {

	public Boolean mailService(Users users){
		String host="mx.valuelabs.net";  
		  final String user="bharath.todupunoori";//change accordingly  
		  final String password="Password#99";//change accordingly  
		    
		  String to=users.getEmailId();//change accordingly
		  
		   //Get the session object  
		   Properties props = new Properties();  
		   props.put("mail.smtp.host",host);  
		   props.put("mail.smtp.auth", "true");  
		   props.put("mail.smtp.port", "25");   
		   Session session = Session.getDefaultInstance(props,  
		    new javax.mail.Authenticator() {  
		      protected PasswordAuthentication getPasswordAuthentication() {  
		    return new PasswordAuthentication(user,password);  
		      }  
		    });  
		  
		   //Compose the message  
		    try {  
		     MimeMessage message = new MimeMessage(session);  
		     message.setFrom(new InternetAddress("bharath.todupunoori@valuelabs.net"));  
		     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		     message.setSubject("Employee Details");
		     message.setText("Name: " +users.getUserName()+" "+" Password:"+users.getPassword());
		    
		       
		    //send the message  
		     Transport.send(message); 
		     
		  
		     System.out.println("message sent successfully...");  
		   
		     } catch (MessagingException e) {e.printStackTrace();}  
		
		return true;
	}
}
