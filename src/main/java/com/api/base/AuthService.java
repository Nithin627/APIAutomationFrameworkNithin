package com.api.base;

import java.util.HashMap;

import io.restassured.response.Response;

public class AuthService extends BaseService {

	private static final String BASE_PATH = "/api/auth/";
//	private static final String AUTH_BASE_URL = "http://64.227.160.186:8080";

	public Response login(Object payload) {

		return postRequest(payload, BASE_PATH + "login");

	}

	public Response signUp(Object payload) {

		return postRequest(payload, BASE_PATH + "signup");

	}

	public Response forgotPassword(String emailAddress) {
		HashMap<String, String> payload = new HashMap();
		payload.put("email", emailAddress);
		return postRequest(payload, BASE_PATH + "forgot-password");

	}
	
	public Response resetPassword(Object payload) {

		return postRequest(payload, BASE_PATH + "reset-password");

	}

	
	

}
