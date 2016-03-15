package com.vlabs.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vlabs.model.Status;

@RestController
public class RabbitMQController {

	private static final String queueName = "springboot";

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	

	public RabbitMQController(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate=rabbitTemplate;
	}
	
	public RabbitMQController() {
	}

	
	@RequestMapping("/sendmessage")
	@HystrixCommand(fallbackMethod = "sendMessageFallBack", threadPoolKey = "sendMessageThreadPool")
	public Status sendMessage(@RequestBody String messsage) {
		Status status = null;
		try {
			if (messsage != null && !messsage.isEmpty()) {
				rabbitTemplate.convertAndSend(queueName, messsage);
				
				status = new Status("Message inserted Successfully", "1");
			} else {
				status = new Status("Please Enter the message", "0");
			}
		}

		catch (Exception e) {
			status = new Status(
					"Exception occurs while inserting message into RabbitMQ: "
							+ e.getMessage(), "0");
		}

		return status;
	}

	public Status sendMessageFallBack(@RequestBody String messsage) {
		Status status = null;
		try {
			if (messsage != null && !messsage.isEmpty()) {
				rabbitTemplate.convertAndSend(queueName, messsage);
				status = new Status("Message inserted Successfully", "1");
			} else {
				status = new Status("Please Enter the message", "0");
			}
		}

		catch (Exception e) {
			status = new Status(
					"Exception occurs while inserting message into RabbitMQ: "
							+ e.getMessage(), "0");
		}

		return status;
	}
	public Status sendMessageThreadPool(@RequestBody String messsage) {
		Status status = null;
		try {
			if (messsage != null && !messsage.isEmpty()) {
				rabbitTemplate.convertAndSend(queueName, messsage);
				status = new Status("Message inserted Successfully", "1");
			} else {
				status = new Status("Please Enter the message", "0");
			}
		}

		catch (Exception e) {
			status = new Status(
					"Exception occurs while inserting message into RabbitMQ: "
							+ e.getMessage(), "0");
		}

		return status;
	}
	
	@RequestMapping("/receivemessage")
	@HystrixCommand(fallbackMethod = "receiveMessageFallBack", threadPoolKey = "receiveMessageThreadPool")
	public Status receiveMessage() {
		Status status = null;
		try
		{
			Object obj=rabbitTemplate.receiveAndConvert(queueName);
			if(null != obj)
				status=new Status("Message from queue :" +(String)obj, "1");
			else
				status=new Status("Queue is Empty ", "0");
		}
		
		catch (Exception e) {
		      status = new Status("Exception occurs while reading message from RabbitMQ" + e.getMessage(), "0");
		    }
		return status;
	}
	public Status receiveMessageFallBack() {
		Status status = null;
		try
		{
			Object obj=rabbitTemplate.receiveAndConvert(queueName);
			if(null != obj)
				status=new Status("Message from queue :" +(String)obj, "1");
			else
				status=new Status("Queue is Empty ", "0");
		}
		
		catch (Exception e) {
		      status = new Status("Exception occurs while reading message from RabbitMQ" + e.getMessage(), "0");
		    }
		return status;
	}
	public Status receiveMessageThreadPool() {
		Status status = null;
		try
		{
			Object obj=rabbitTemplate.receiveAndConvert(queueName);
			if(null != obj)
				status=new Status("Message from queue :" +(String)obj, "1");
			else
				status=new Status("Queue is Empty ", "0");
		}
		
		catch (Exception e) {
		      status = new Status("Exception occurs while reading message from RabbitMQ" + e.getMessage(), "0");
		    }
		return status;
	}
	
	

}
