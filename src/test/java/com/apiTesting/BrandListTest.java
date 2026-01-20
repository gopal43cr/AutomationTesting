package com.apiTesting;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BrandListTest {

    @Test
    public void getAllBrandsList() {
        System.out.println("===== GET ALL BRANDS LIST =====");

        Response res = given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.JSON)
            .when()
                .get("/brandsList")
            .then()
                .extract().response();

        // Print status code and body
        System.out.println("Response Code: " + res.getStatusCode());
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        // Optional assertions
        res.then()
           .statusCode(200)
           .body("brands", notNullValue())
           .time(lessThan(5000L));
    }

    @Test
    public void putToAllBrandsList() {
        System.out.println("===== PUT ALL BRANDS LIST =====");

        Response res = given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.JSON)
            .when()
                .put("/brandsList")
            .then()
                .extract().response();

        // Print status code and body
        System.out.println("Response Code: " + res.getStatusCode());
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        // Optional assertions
        res.then()
           .statusCode(405)
           .body(containsString("This request method is not supported"));
    }
}

