package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.models.request.LoginRequest;
import com.api.models.request.SignUpRequest;
import com.api.models.request.UpdateProfileRequest;
import com.api.models.response.LoginResponse;
import com.api.models.response.UserProfileResponse;

import io.restassured.response.Response;

public class UpdateProfileAPITest {

	@Test(description = "Update profile")
	public void updateProfileTest() {

//		Login into the application -> goto profile page

		AuthService authService = new AuthService();
		Response response = authService.login(new LoginRequest("Nithin", "nithin123@"));
		LoginResponse loginResponse = response.as(LoginResponse.class);

		UserProfileManagementService userProfileManagementService = new UserProfileManagementService();
		response = userProfileManagementService.getProfile(loginResponse.getToken());
		System.out.println(response.asPrettyString());
		UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);
		Assert.assertEquals(userProfileResponse.getUsername(), "Nithin");

		UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest.Builder().firstName("Manu")
				.email("manu432@gmail.com").build();
		response = userProfileManagementService.updateProfile(loginResponse.getToken(), updateProfileRequest);

		System.out.println(response.asPrettyString());
//		Assert.assertEquals(response.statusCode(), 200);

	}

}
