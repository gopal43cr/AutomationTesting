package com.apiTesting;

import static org.hamcrest.Matchers.lessThan;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SearchProduct {

    @Test
    public void postToSearchProduct() {
        System.out.println("===== POST TO SEARCH PRODUCT =====");

        Response res = RestAssured.given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.URLENC)
                .formParam("search_product", "top")
            .when()
                .post("/searchProduct")
            .then()
                .extract().response();

        System.out.println("Response Code: " + res.getStatusCode());
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        res.then()
           .statusCode(200)
           .body(notNullValue());
    }

    @Test
    public void postToSearchProductWithoutParam() {
        System.out.println("===== POST TO SEARCH PRODUCT WITHOUT PARAM =====");

        Response res = RestAssured.given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.URLENC)
            .when()
                .post("/searchProduct")
            .then()
                .extract().response();

        System.out.println("Response Code: " + res.getStatusCode());
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        res.then()
           .statusCode(400)
           .body(notNullValue());
    }
}
