package com.apiTesting;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class BrandListTest {
	@Test
    public void getAllBrandsList() {

		given()
        .baseUri("https://automationexercise.com/api")
        .contentType(ContentType.JSON)
    .when()
        .get("/brandsList")
    .then()
        // Functional validation
        .statusCode(200)
        .body("brands", notNullValue())

        // Performance validation
        .time(lessThan(5000L));
    }
        
    @Test
    public void putToAllBrandsList() {
    	
        given()
            .baseUri("https://automationexercise.com/api")
            .contentType(ContentType.JSON)
        .when()
            .put("/brandsList")
        .then()
            // Validate response code
            .statusCode(405)

            // Validate response message
            .body(containsString("This request method is not supported"));
    }

}
