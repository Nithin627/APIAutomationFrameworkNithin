package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.models.request.SignUpRequest;

import io.restassured.response.Response;

public class ForgotPasswordAPITest {

	@Test(description = "Forgot password test")
	public void forgotPasswordTest() {

		
		AuthService authService = new AuthService();
		Response response = authService.forgotPassword("nithinpaur@gmail.com");
		System.out.println(response.asPrettyString());

//		Assert.assertEquals(response.asPrettyString(), "If your email exists in our system, you will receive reset instructions.");
		Assert.assertEquals(response.statusCode(), 200);

	}

}
