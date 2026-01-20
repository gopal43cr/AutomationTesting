package com.apiTesting;


import org.testng.Assert;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginTest {

    private static final String BASE_URI = "https://automationexercise.com/api";

    // ---------- API 7 ----------
    // POST: Verify Login with VALID details
    @Test
    public void verifyLoginWithValidDetails() {

        System.out.println("===== VERIFY LOGIN WITH VALID DETAILS =====");

        Response res =
            RestAssured.given()
                .baseUri(BASE_URI)
                .contentType(ContentType.URLENC)
                .formParam("email", "harshikasingh0312@gmail.com")
                .formParam("password", "Harshika26")
            .when()
                .post("/verifyLogin")
            .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(res.asPrettyString());

        int responseCode = res.jsonPath().getInt("responseCode");
        String message = res.jsonPath().getString("message");

        Assert.assertEquals(responseCode, 200);
        Assert.assertEquals(message, "User exists!");
    }

    // ---------- API 10 ----------
    // POST: Verify Login with INVALID details
    @Test
    public void verifyLoginWithInvalidDetails() {

        System.out.println("===== VERIFY LOGIN WITH INVALID DETAILS =====");

        Response res =
            RestAssured.given()
                .baseUri(BASE_URI)
                .contentType(ContentType.URLENC)
                .formParam("email", "invalid@example.com")
                .formParam("password", "wrongpass")
            .when()
                .post("/verifyLogin")
            .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(res.asPrettyString());

        int responseCode = res.jsonPath().getInt("responseCode");
        String message = res.jsonPath().getString("message");

        Assert.assertEquals(responseCode, 404);
        Assert.assertEquals(message, "User not found!");
    }

    // ---------- API 8 ----------
    // POST: Verify Login WITHOUT email
    @Test
    public void verifyLoginWithoutEmail() {

        System.out.println("===== VERIFY LOGIN WITHOUT EMAIL =====");

        Response res =
            RestAssured.given()
                .baseUri(BASE_URI)
                .contentType(ContentType.URLENC)
                .formParam("password", "test123")
            .when()
                .post("/verifyLogin")
            .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(res.asPrettyString());

        int responseCode = res.jsonPath().getInt("responseCode");
        String message = res.jsonPath().getString("message");

        Assert.assertEquals(responseCode, 400);
        Assert.assertEquals(
            message,
            "Bad request, email or password parameter is missing in POST request."
        );
    }

    // ---------- API 9 ----------
    // DELETE: Verify Login
    @Test
    public void deleteVerifyLogin() {

        System.out.println("===== DELETE VERIFY LOGIN =====");

        Response res =
            RestAssured.given()
                .baseUri(BASE_URI)
            .when()
                .delete("/verifyLogin")
            .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(res.asPrettyString());

        int responseCode = res.jsonPath().getInt("responseCode");
        String message = res.jsonPath().getString("message");

        Assert.assertEquals(responseCode, 405);
        Assert.assertEquals(message, "This request method is not supported.");
    }
}
