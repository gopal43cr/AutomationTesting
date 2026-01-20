package com.apiTesting;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProductsListTest {

    @Test
    public void getAllProductsList() {
        System.out.println("===== GET ALL PRODUCTS LIST =====");

        Response res = given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.JSON)
            .when()
                .get("/productsList")
            .then()
                .extract().response();

        System.out.println("Response Code: " + res.getStatusCode());
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        res.then()
           .statusCode(200)
           .body("products", notNullValue())
           .time(lessThan(5000L));
    }

    @Test
    public void postToProductsList() {
        System.out.println("===== POST TO PRODUCTS LIST =====");

        Response res = given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.JSON)
            .when()
                .post("/productsList")
            .then()
                .extract().response();

        System.out.println("Response Code: " + res.getStatusCode());
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        res.then()
           .statusCode(405)
           .body(containsString("This request method is not supported"));
    }
}
