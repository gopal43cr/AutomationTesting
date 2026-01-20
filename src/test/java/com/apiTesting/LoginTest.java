package com.apiTesting;

import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class LoginTest {

    //POST To Verify Login with valid details
    @Test
    public void postToVerifyLoginWithValidDetails() {
        RestAssured.given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.URLENC)
                .formParam("email", "harshikasingh0312@gmail.com")
                .formParam("password", "Harshika26")
        .when()
                .post("/verifyLogin")
        .then()
                .statusCode(200)
                .body(notNullValue()); // message: User exists!
    }

    // POST To Verify Login without email parameter
    @Test
    public void postToVerifyLoginWithoutEmail() {
        RestAssured.given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.URLENC)
                .formParam("password", "test123")
        .when()
                .post("/verifyLogin")
        .then()
                .statusCode(400)
                .body(notNullValue()); // message: Bad request, email or password missing
    }

    // DELETE To Verify Login
    @Test
    public void deleteVerifyLogin() {
        RestAssured.given()
                .baseUri("https://automationexercise.com/api")
        .when()
                .delete("/verifyLogin")
        .then()
                .statusCode(405)
                .body(notNullValue()); // message: This request method is not supported
    }

    // POST To Verify Login with invalid details
    @Test
    public void postToVerifyLoginWithInvalidDetails() {
        RestAssured.given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.URLENC)
                .formParam("email", "invalid@example.com")
                .formParam("password", "wrongpass")
        .when()
                .post("/verifyLogin")
        .then()
                .statusCode(404)
                .body(notNullValue()); // message: User not found
    }
}
