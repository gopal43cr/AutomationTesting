package com.apiTesting;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.Assert;
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
            		.statusCode(200)
            		.body("brands", notNullValue())
            		.time(lessThan(5000L))
            		.extract()
            		.response();


        System.out.println("Response Code: " + res.getStatusCode());
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);
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
        			.statusCode(200)
        			.extract()
        			.response();

    System.out.println(res.asPrettyString()); 
    int responseCode = res.jsonPath().getInt("responseCode"); 
    String message = res.jsonPath().getString("message"); 
    Assert.assertEquals(responseCode, 405); 
    Assert.assertEquals( message, "This request method is not supported." );
}
}
