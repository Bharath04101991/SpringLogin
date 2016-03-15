package com.vlabs;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.vlabs.controller.RestLoginController;
import com.vlabs.service.LoginService;
import com.vlabs.service.RegistrationService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes=WebAppConfig.class)
@IntegrationTest("server.port:12345")
public class RestLoginControllerTest {
	private static final String mailId="bharath.todupunoori@valuelabs.net";

	@Autowired
	LoginService loginService;
	@Autowired
	RegistrationService registrationService;
	
	@Before
	public void setup(){
		RestAssuredMockMvc.standaloneSetup(new RestLoginController(loginService, registrationService));
		//RestAssured.registerParser("text/plain", Parser.TEXT);
	}
	
	/*@Test
	public void deleteTest(){
	given()
	  .param("id",id)
	  .when()
	  .delete("/rest_delete")
	  .then()
	  .statusCode(200)
	  .body("response.message",equalTo("User Deleted"))
	  .body("response.result_code", equalTo("1"));
	System.out.println("user deleted");
	}*/
	
	@Test
	public void getPasswordTest(){
		String postBody = new String(mailId);
		given()
		.body(postBody)
		.when()
		.post("/rest_getpassword")
		.then()
		.statusCode(200)
		.body("response.message", containsString("Credentials are sent to"))
		.body("response.result_code", equalTo("1"));
	}
	
	@After
	  public void tearDown() {
	    RestAssuredMockMvc.reset();
	  }
}
