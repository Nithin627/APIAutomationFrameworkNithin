package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.models.request.LoginRequest;
import com.api.models.request.SignUpRequest;
import com.api.models.response.LoginResponse;
import com.api.models.response.UserProfileResponse;

import io.restassured.response.Response;

public class GETProfileAPITest {

	@Test(description = "get profile info")
	public void getProfileInfoTest() {

//		Login into the application -> goto profile page

		AuthService authService = new AuthService();
		Response response = authService.login(new LoginRequest("Nithin", "nithin123@"));
		LoginResponse loginResponse = response.as(LoginResponse.class);
		System.out.println(loginResponse.getToken());
		String token = loginResponse.getToken();

		UserProfileManagementService userProfileManagementService = new UserProfileManagementService();
		response = userProfileManagementService.getProfile(token);
		UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);
		System.out.println(userProfileResponse.getId());

//		System.out.println(response.asPrettyString());
		Assert.assertEquals(response.statusCode(), 200);

	}

}
