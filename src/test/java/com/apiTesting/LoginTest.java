package com.apiTesting;

import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginTest {

    // API 7: POST To Verify Login with valid details
    @Test
    public void postToVerifyLoginWithValidDetails() {
        System.out.println("===== VERIFY LOGIN WITH VALID DETAILS =====");

        Response res =
            RestAssured.given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.URLENC)
                .formParam("email", "harshikasingh0312@gmail.com")
                .formParam("password", "Harshika26")
            .when()
                .post("/verifyLogin")
            .then()
                .extract().response();

        System.out.println("Response Code: " + res.getStatusCode());
        System.out.println(res.jsonPath().prettify());

        res.then()
           .statusCode(200)
           .body(notNullValue());
    }

    // API 8: POST To Verify Login without email parameter
    @Test
    public void postToVerifyLoginWithoutEmail() {
        System.out.println("===== VERIFY LOGIN WITHOUT EMAIL =====");

        Response res =
            RestAssured.given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.URLENC)
                .formParam("password", "test123")
            .when()
                .post("/verifyLogin")
            .then()
                .extract().response();

        System.out.println("Response Code: " + res.getStatusCode());
        System.out.println(res.jsonPath().prettify());

        res.then()
           .statusCode(400)
           .body(notNullValue());
    }

    // API 9: DELETE To Verify Login
    @Test
    public void deleteVerifyLogin() {
        System.out.println("===== DELETE VERIFY LOGIN =====");

        Response res =
            RestAssured.given()
                .baseUri("https://automationexercise.com/api")
            .when()
                .delete("/verifyLogin")
            .then()
                .extract().response();

        System.out.println("Response Code: " + res.getStatusCode());
        System.out.println(res.jsonPath().prettify());

        res.then()
           .statusCode(405)
           .body(notNullValue());
    }

    // API 10: POST To Verify Login with invalid details
    @Test
    public void postToVerifyLoginWithInvalidDetails() {
        System.out.println("===== VERIFY LOGIN WITH INVALID DETAILS =====");

        Response res =
            RestAssured.given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.URLENC)
                .formParam("email", "invalid@example.com")
                .formParam("password", "wrongpass")
            .when()
                .post("/verifyLogin")
            .then()
                .extract().response();

        System.out.println("Response Code: " + res.getStatusCode());
        System.out.println(res.jsonPath().prettify());

        res.then()
           .statusCode(404)
           .body(notNullValue());
    }
}
