package com.example.order_service;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:8.3.0");

	@LocalServerPort
	private int port;

	@BeforeEach
	void setup(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mySQLContainer.start();
	}


	@Test
	void shouldBeAbleToPlaceOrder() {
		String requestBody = """
				{
				    "skuCode": "iphone-15",
				    "price": 1000,
				    "quantity": 2
				}
				""";
		RestAssured.given()
				.body(requestBody)
				.contentType("application/json")
				.when()
				.post("/api/order")
				.then()
				.statusCode(201);
	}

}
