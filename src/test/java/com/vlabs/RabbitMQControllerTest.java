package com.vlabs;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.vlabs.controller.RabbitMQController;
import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=WebAppConfig.class)
@IntegrationTest("server.port:8080")
public class RabbitMQControllerTest {
	
	//private final static String queueName = "spring-boot";

	private final static String MESSAGE = "test";
	  
	@Autowired
	RabbitTemplate template;
	
	@Before
	public void setup(){
		RestAssuredMockMvc.standaloneSetup(new RabbitMQController(template));
	}
	
	@Test
	public void sendMessageTest(){
	given()
	.param("message", MESSAGE)
	.when()
	.post("/sendmessage")
	.then()
	.statusCode(200)
	.body("response.message",equalTo("Message inserted Successfully"))
	.body("response.result_code", equalTo("1"));
	System.out.println("tested sucessfully...!!!");
	//template.receive();
	}

	 @Test
	  public void receiveMessageTest() {
	    template.convertAndSend(MESSAGE);
	    given().when()
	        .post("/receivemessage")
	        .then()
	        .statusCode(200)
	        .body("response.message", containsString("Message from queue :"))
	        .body("response.result_code", equalTo("1"));
	  }
}
