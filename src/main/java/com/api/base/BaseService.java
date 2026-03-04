package com.api.base;

import static io.restassured.RestAssured.given;

import com.api.filters.LoggingFilter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseService {
//	To handle base URI
//	To create the request
//	To handle the response

	private static final String BASE_URL = "http://64.227.160.186:8080";
	private RequestSpecification requestSpecification;

//	Static block :- executes once and available for every one
	{
		RestAssured.filters(new LoggingFilter());// Filter calling in base service
	}

	public BaseService() {

		requestSpecification = given().baseUri(BASE_URL);
	}

	protected Response postRequest(Object payload, String endpoint) {

		return requestSpecification.contentType(ContentType.JSON).body(payload).post(endpoint);

	}

	protected Response getRequest(String endpoint) {

		return requestSpecification.get(endpoint);

	}

	protected void setAuthToken(String token) {

		requestSpecification.header("Authorization", "Bearer " + token);

	}

	protected Response putRequest(Object payload, String endpoint) {

		return requestSpecification.contentType(ContentType.JSON).body(payload).put(endpoint);

	}

//	public BaseService() {
//		requestSpecification = given();
//	}

//	protected Response postRequest(String baseUri, Object payload, String endpoint) {
//
//		return requestSpecification.baseUri(baseUri).contentType(ContentType.JSON).body(payload).post(endpoint);
//
//	}
}
