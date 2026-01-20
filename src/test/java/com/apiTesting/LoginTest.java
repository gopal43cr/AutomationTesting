package com.apiTesting;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginTest {
	 @Test
	    public void postToVerifyLoginWithValidDetails() {
	    	
	    	System.out.println("Verify Login with Valid Details");
	        RestAssured.baseURI = "https://automationexercise.com/api";

	        RequestSpecification httpReq = RestAssured.given()
	                                        .contentType(ContentType.URLENC)
	                                        .formParam("email", "harshikasingh0312@gmail.com")
	                                        .formParam("password", "Harshika26");

	        Response res = httpReq.request(Method.POST, "/verifyLogin");

	        String jsonPretty = res.jsonPath().prettify();
	        System.out.println(jsonPretty);

	        // Print status code
	        System.out.println("Status Code: " + res.getStatusCode());
	        System.out.println("-------------------------------------------------------------------------");
	    }
	    @Test
	    public void postToVerifyLoginWithoutEmail() {
	    	
	    	System.out.println("Verify Login without Email");
	        RestAssured.baseURI = "https://automationexercise.com/api";

	        // Only password is sent, email is missing
	        RequestSpecification httpReq = RestAssured.given()
	                                        .contentType(ContentType.URLENC)
	                                        .formParam("password", "test123");

	        Response res = httpReq.request(Method.POST, "/verifyLogin");

	        // Print full response
	        String jsonPretty = res.jsonPath().prettify();
	        System.out.println(jsonPretty);

	        // Print status code
	        System.out.println("Status Code: " + res.getStatusCode());
	        System.out.println("-------------------------------------------------------------------------");
	    }
	    @Test
	    public void deleteVerifyLogin() {
	    	
	    	System.out.println("Delete to Verify Login");
	        RestAssured.baseURI = "https://automationexercise.com/api";

	        RequestSpecification httpReq = RestAssured.given();

	        // Send DELETE request
	        Response res = httpReq.request(Method.DELETE, "/verifyLogin");

	        // Print full response
	        String jsonPretty = res.jsonPath().prettify();
	        System.out.println(jsonPretty);

	        // Print status code
	        System.out.println("Status Code: " + res.getStatusCode());
	        System.out.println("-------------------------------------------------------------------------");
	    }
	    @Test
	    public void postToVerifyLoginWithInvalidDetails() {

	    	System.out.println(" Verify Login with invalid details");
	        RestAssured.baseURI = "https://automationexercise.com/api";

	        // Send invalid credentials
	        RequestSpecification httpReq = RestAssured.given()
	                                        .contentType(ContentType.URLENC)
	                                        .formParam("email", "invalid@example.com")
	                                        .formParam("password", "wrongpass");

	        Response res = httpReq.request(Method.POST, "/verifyLogin");

	        // Print full response
	        String jsonPretty = res.jsonPath().prettify();
	        System.out.println(jsonPretty);

	        // Print status code
	        System.out.println("Status Code: " + res.getStatusCode());
	        System.out.println("-------------------------------------------------------------------------");
	    }
}
