package com.apiTesting;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginTest {

    //POST To Verify Login with valid details
    @Test
    public void postToVerifyLoginWithValidDetails() {
        Response response = RestAssured.given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.URLENC)
                .formParam("email", "harshikasingh0312@gmail.com")
                .formParam("password", "Harshika26")
        .when()
                .post("/verifyLogin")
        .then()
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();
        response.prettyPrint();
    }

    // POST To Verify Login without email parameter
    @Test
    public void postToVerifyLoginWithoutEmail() {
        Response response = RestAssured.given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.URLENC)
                .formParam("password", "test123")
        .when()
                .post("/verifyLogin")
        .then()
                .statusCode(200)
                .body(notNullValue())
                .extract()
                .response();
        response.prettyPrint();
    }

    // DELETE To Verify Login
    @Test
    public void deleteVerifyLogin() {
        Response response = RestAssured.given()
                .baseUri("https://automationexercise.com/api")
        .when()
                .delete("/verifyLogin")
        .then()
                .statusCode(200)
                .body(notNullValue()) // message: This request method is not supported
	     // Performance validation
	        .time(lessThan(1500L))
	        .extract()
	        .response();
        response.prettyPrint();
    }

    // POST To Verify Login with invalid details
    @Test
    public void postToVerifyLoginWithInvalidDetails() {
        Response response = RestAssured.given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.URLENC)
                .formParam("email", "invalid@example.com")
                .formParam("password", "wrongpass")
        .when()
                .post("/verifyLogin")
        .then()
                .statusCode(200)
                .body(notNullValue()) // message: User not found
	     // Performance validation
	        .time(lessThan(1500L))
	        .extract()
	        .response();
        response.prettyPrint();
    }
}
