package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.models.request.SignUpRequest;

import io.restassured.response.Response;

public class AccountCreationAPITest {

	@Test(description = "Create account")
	public void createAccountTest() {

		SignUpRequest signUpRequest = new SignUpRequest.Builder().userName("Ram").email("Ram@gmail.com")
				.firstName("Ram100").lastName("leela").password("123458").mobileNumber("7888888888").build();
		AuthService authService = new AuthService();
		Response response = authService.signUp(signUpRequest);
		System.out.println(response.asPrettyString());

		Assert.assertEquals(response.asPrettyString(), "");
		Assert.assertEquals(response.statusCode(), 200);

	}

}
