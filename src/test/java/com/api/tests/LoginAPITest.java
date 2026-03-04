package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;

import io.restassured.response.Response;

@Listeners(com.api.listeners.TestListener.class)
public class LoginAPITest {

	@Test(description = "Verify if login API is working...")
	public void loginTest() {

//		https://swift.techwithjatin.com//api/auth/login
//		https://tech-with-jatin.notion.site/E2E-Automation-Framework-Creation-1526d427c22780328b8fff211ee050b7#1526d427c227817daaf8c34eb4ff602b
//		http://64.227.160.186:8080/swagger-ui/index.html

//		LoginRequest loginRequest = new LoginRequest("Nithin", "nithin123@");
//		LoginRequest loginRequest = new LoginRequest("Ram", "123458");
		LoginRequest loginRequest = new LoginRequest.Builder().username("Nithin").password("nithin123@").build();
		AuthService authService = new AuthService();
		Response response = authService.login(loginRequest);
		System.out.println(response.asPrettyString());

		LoginResponse loginResponse = response.as(LoginResponse.class);
		System.out.println(loginResponse.getToken());
		System.out.println(loginResponse.getEmail());
		System.out.println(loginResponse.getId());
		System.err.println(loginResponse.getRoles());

		Assert.assertTrue(loginResponse.getToken() != null);
		Assert.assertEquals(loginResponse.getEmail(), "nithinpaur@gmail.com");

	}

}
