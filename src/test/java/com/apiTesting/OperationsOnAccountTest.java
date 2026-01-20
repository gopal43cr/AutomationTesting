package com.apiTesting;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OperationsOnAccountTest {
	@Test
    public void postToCreateUserAccount() {

    	System.out.println("Create/Register User Account");
        RestAssured.baseURI = "https://automationexercise.com/api";

        RequestSpecification httpReq = RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("name", "John Doe")
            .formParam("email", "johndoe123@example.com")
            .formParam("password", "Test@1234")
            .formParam("title", "Mr")
            .formParam("birth_date", "15")
            .formParam("birth_month", "August")
            .formParam("birth_year", "1990")
            .formParam("firstname", "John")
            .formParam("lastname", "Doe")
            .formParam("company", "ABC Corp")
            .formParam("address1", "123 Main Street")
            .formParam("address2", "Apartment 4B")
            .formParam("country", "India")
            .formParam("zipcode", "700001")
            .formParam("state", "West Bengal")
            .formParam("city", "Kolkata")
            .formParam("mobile_number", "9876543210");

        // Send POST request
        Response res = httpReq.request(Method.POST, "/createAccount");

        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        // Print status code
        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("-------------------------------------------------------------------------");
    }
    @Test
    public void deleteUserAccount() {
    	
    	System.out.println("Delete User Account");
        RestAssured.baseURI = "https://automationexercise.com/api";

        RequestSpecification httpReq = RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("email", "johndoe123@example.com")
            .formParam("password", "Test@1234");

        // Send DELETE request
        Response res = httpReq.request(Method.DELETE, "/deleteAccount");

        // Print full response
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        // Print status code
        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("-------------------------------------------------------------------------");

    }
    @Test
    public void putToUpdateUserAccount() {
    	
    	System.out.println("Update User Account");
        RestAssured.baseURI = "https://automationexercise.com/api";

        RequestSpecification httpReq = RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("name", "John Doe Updated")
            .formParam("email", "johndoe123@example.com")
            .formParam("password", "Test@1234")
            .formParam("title", "Mr")
            .formParam("birth_date", "15")
            .formParam("birth_month", "August")
            .formParam("birth_year", "1990")
            .formParam("firstname", "John")
            .formParam("lastname", "Doe")
            .formParam("company", "ABC Corp")
            .formParam("address1", "123 Main Street")
            .formParam("address2", "Apartment 4B")
            .formParam("country", "India")
            .formParam("zipcode", "700001")
            .formParam("state", "West Bengal")
            .formParam("city", "Kolkata")
            .formParam("mobile_number", "9876543210");

        // Send PUT request
        Response res = httpReq.request(Method.PUT, "/updateAccount");

        // Print full response
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        // Print status code
        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("-------------------------------------------------------------------------");

    }
    @Test
    public void getUserDetailByEmail() {
    	
    	System.out.println("Get User Details By Email");
        RestAssured.baseURI = "https://automationexercise.com/api";

        RequestSpecification httpReq = RestAssured.given()
                                        .queryParam("email", "johndoe123@example.com"); // Email to search

        Response res = httpReq.request(Method.GET, "/getUserDetailByEmail");

        // Print full response
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        // Print status code
        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("-------------------------------------------------------------------------");

    }
}
