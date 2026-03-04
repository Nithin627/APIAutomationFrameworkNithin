package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class DemoAPITest {

	@Test(description = "Verify if login API is working...")
	public void loginTest() {

//		https://swift.techwithjatin.com//api/auth/login
//		https://tech-with-jatin.notion.site/E2E-Automation-Framework-Creation-1526d427c22780328b8fff211ee050b7#1526d427c227817daaf8c34eb4ff602b
//		http://64.227.160.186:8080/swagger-ui/index.html

//		baseURI = "http://64.227.160.186:8080";
//		RequestSpecification rs = RestAssured.given();
//		RequestSpecification rs2 = RestAssured.given().header("Content-Type", "application/json");
//		RequestSpecification rs3 = RestAssured.given().header("Content-Type", "application/json")
//				.body("{\r\n" + "  \"username\": \"Nithin\",\r\n" + "  \"password\": \"nithin123@\"\r\n" + "}");
//		Response response=rs3.post("/api/auth/login");

		Response response = given().baseUri("http://64.227.160.186:8080").header("Content-Type", "application/json")
				.body("{\r\n" + "  \"username\": \"Nithin\",\r\n" + "  \"password\": \"nithin123@\"\r\n" + "}")
				.post("/api/auth/login");
		System.out.println(response.asPrettyString());

		Assert.assertEquals(response.getStatusCode(), 200);

//		given()
//        .baseUri("http://64.227.160.186:8080/api/auth/")
//        .header("Content-type","application/json")
//        .body("{\"username\": \"Nithin\", \"password\": \"nithin123@\"}")
//    .when()
//        .log().all()
//        .post("/login")
//    .then()
//        .log().all()
//        .statusCode(200)
//        .time(lessThan(3000L));

	}

}
