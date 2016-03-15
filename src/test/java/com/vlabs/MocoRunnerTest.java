package com.vlabs;

import static com.github.dreamhead.moco.Moco.by;
import static com.github.dreamhead.moco.Moco.httpserver;
import static com.github.dreamhead.moco.Moco.log;
import static com.github.dreamhead.moco.Moco.pathResource;
import static com.github.dreamhead.moco.Moco.uri;
import static com.github.dreamhead.moco.Runner.runner;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static com.jayway.restassured.RestAssured.*;

import java.io.IOException;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runner;
import com.jayway.restassured.RestAssured;

public class MocoRunnerTest {
	private Runner runner;

	private HttpServer server = null;

	@Before
	public void setup() {
		server = httpserver(12306, log());

	}

	@After
	public void tearDown() {
		if (null != runner)
			runner.stop();
	}

	@Test
	public void moco_restAssuredTest() {

		server.post(by(uri("/rest_getpassword"))).response(
				pathResource("Response.xml"));
		runner = runner(server);
		runner.start();

		RestAssured.baseURI = "http://localhost:12306";
		String mailId = new String("bharath.todupunoori@valuelabs.net");

		given().body(mailId)
				.log()
				.all()
				.when()
				.post("/rest_getpassword")
				.then()
				.log()
				.all()
				.statusCode(200)
				.body("response.message",
						equalTo("Credentials are sent tobharathsai007@gmail.com"));
	}

	@Test
	public void mocoTest() throws IOException {
		server.post(by(uri("/getPassword"))).response(
				pathResource("Response.xml"));
		runner = runner(server);
		runner.start();

		Content content = Request.Post("http://localhost:12306/getPassword")
				.execute().returnContent();
		assertTrue(content.asString().contains(
				"Credentials are sent tobharathsai007@gmail.com"));

	}
	/*
	 * <dependency> <groupId>org.apache.httpcomponents</groupId>
	 * <artifactId>fluent-hc</artifactId> <version>4.3.6</version>
	 * <scope>test</scope> </dependency>
	 * 
	 * <dependency> <groupId>com.github.dreamhead</groupId>
	 * <artifactId>moco-core</artifactId> <version>0.10.0</version>
	 * </dependency>
	 */

}