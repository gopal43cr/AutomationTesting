package com.apiTesting;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import io.restassured.response.Response;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class BrandListTest {
	@Test
    public void getAllBrandsList() {

		Response response = given()
        .baseUri("https://automationexercise.com/api")
        .contentType(ContentType.JSON)
    .when()
        .get("/brandsList")
    .then()
        // Functional validation
        .statusCode(200)
        .body("brands", notNullValue())

        // Performance validation
        .time(lessThan(4500L))
        .extract()
        .response();
		
		response.prettyPrint();
    }
        
    @Test
    public void putToAllBrandsList() {
    	    
       Response response = 
        given()
            .baseUri("https://automationexercise.com/api")
            .contentType(ContentType.JSON)
        .when()
            .put("/brandsList")
        .then()
            // Validate response code
            .statusCode(200)

            // Validate response message
            .body(containsString("This request method is not supported"))
            // Performance validation
            .time(lessThan(1500L))
            .extract()
	        .response();
       
       response.prettyPrint();
        
    }

}
