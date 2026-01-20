package com.apiTesting;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import io.restassured.response.Response;

import org.testng.Assert;
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
            		.statusCode(200)
            		.body("brands", notNullValue())
                .time(lessThan(2000L))
                .extract()
                .response();

        // Print status code and body
        System.out.println("Response Code: " + res.getStatusCode());
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

//        // Optional assertions
//        res.then()
           
           
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