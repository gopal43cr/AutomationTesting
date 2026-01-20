package com.apiTesting;

import static org.hamcrest.Matchers.lessThan;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class SearchProduct {

    // API 5: POST To Search Product
    @Test
    public void postToSearchProduct() {
        RestAssured.given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.URLENC)
                .formParam("search_product", "top")
        .when()
                .post("/searchProduct")
        .then()
                .statusCode(200)
                .body(notNullValue()) // JSON: Searched products list
	     // Performance validation
	        .time(lessThan(1500L));
    }

    // API 6: POST To Search Product without search_product parameter
    @Test
    public void postToSearchProductWithoutParam() {
        RestAssured.given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.URLENC)
        .when()
                .post("/searchProduct")
        .then()
                .statusCode(200)
                .body(notNullValue())
	     // Performance validation
	        .time(lessThan(1500L));
    }
}
