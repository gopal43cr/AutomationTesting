package com.apiTesting;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;


public class ProductsListTest {
	@Test
	public void getAllProductsList() {

        given()
            .baseUri("https://automationexercise.com/api")
            .contentType(ContentType.JSON)
        .when()
            .get("/productsList")
        .then()
            // Functional validation
            .statusCode(200)
            .body("products", notNullValue())

            // Performance validation (same as responseTime <= 5000)
            .time(lessThan(5000L));
    }
 
	@Test
    public void postToProductsList() {
    	
	        given()
	            .baseUri("https://automationexercise.com/api")
	            .contentType(ContentType.JSON)
	        .when()
	            .post("/productsList")
	        .then()
	            // Negative test: POST not allowed
	            .statusCode(405)
	            .body(containsString("This request method is not supported"));
    }
}
