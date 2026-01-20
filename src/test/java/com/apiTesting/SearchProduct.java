package com.apiTesting;

import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

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

        Response res =
            RestAssured.given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.URLENC)
            .when()
                .post("/searchProduct")
            .then()
                .statusCode(200)      // HTTP-level validation
                .extract()
                .response();

        System.out.println(res.asPrettyString());

        int responseCode = res.jsonPath().getInt("responseCode");
        String message = res.jsonPath().getString("message");

        Assert.assertEquals(responseCode, 400);
        Assert.assertEquals(
            message,
            "Bad request, search_product parameter is missing in POST request."
        );
    }

}
