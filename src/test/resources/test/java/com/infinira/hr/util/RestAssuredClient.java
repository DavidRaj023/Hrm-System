package com.infinira.hr.util;

import java.text.MessageFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredClient {
	
	public static Response post(String postUrl, String postData) {
		try {
			validate("post", postUrl);
			Response response = RestAssured.
				given().
					header(CONTENT_TYPE, APPLICAION_JSON).
					body(postData).
				when().
					post(postUrl).
				then().
					extract().response();
			return response;
		} catch (Throwable th) {
			throw new RuntimeException(ERROR_POST, th);
		}
	}

	public static Response get(String getUrl) {
		try {
			validate("get", getUrl);
			Response response = RestAssured.
				when().
					get(getUrl).
				then().
					extract().response();
			return response;
		} catch (Throwable th) {
			throw new RuntimeException(ERROR_GET, th);
		}
	}
	
	public static <T> T get(String getUrl, Class<T> valueObj) {
		try {
			validate("get", getUrl);
			Response response = RestAssured.
				when().
					get(getUrl).
				then().
					extract().response();
			return convertJsonToPojo(response.asString(), valueObj);
		} catch (Throwable th) {
			throw new RuntimeException(ERROR_GET, th);
		}
	}

	public static Response put(String putUrl, String putData) {
		try {
			validate("put", putUrl);
			Response response = RestAssured.
				given().
					header(CONTENT_TYPE, APPLICAION_JSON).
					body(putData).
				when().
					put(putUrl).
				then().
					extract().response();
			return response;
		} catch (Throwable th) {
			throw new RuntimeException(ERROR_PUT, th);
		}
	}

	public static Response delete(String deleteUrl) {
		try {
			validate("delete", deleteUrl);
			Response response = RestAssured.
				when().
					delete(deleteUrl).
				then().
					extract().response();
			return response;
		} catch (Throwable th) {
			throw new RuntimeException(ERROR_DELETE, th);
		}
	}
	

	public static String convertPojoToJson(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(obj);
		} catch (Throwable th) {
			throw new RuntimeException(FAILED_POJO_JSON, th);
		}
	}

	public static <T> T convertJsonToPojo(String json, Class<T> valueObj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, valueObj);
		} catch (Throwable th) {
			throw new RuntimeException(FAILED_JSON_POJO, th);
		}
	}

	public static void validate(String paramName, String paramValue) {
		if (paramName == null || paramName.isEmpty()) {
			throw new RuntimeException(EMPTY_PARAM);
		}
		if (paramValue == null || paramValue.isEmpty()) {
			throw new RuntimeException(MessageFormat.format(INVALID_URL, paramName));
		}
	}
	
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String APPLICAION_JSON = "application/json";
	private static final String FAILED_POJO_JSON = "Failed to convert Pojo to Json";
	private static final String FAILED_JSON_POJO = "Failed to convert Json to Pojo";
	private static final String ERROR_POST = "Failed to create an employee";
	private static final String ERROR_GET = "Failed to get a record";
	private static final String ERROR_DELETE = "Failed to delete a record";
	private static final String ERROR_PUT = "Failed to update an employee";
	private static final String EMPTY_PARAM = "Empty parameter cannot be accepted";
	private static final String INVALID_URL = "Url cannot be empty in {0} method";


}
