package com.apiTesting;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SearchProduct {
	@Test
    public void postToSearchProduct() {
    	
    	System.out.println("POST to Search Product");
        RestAssured.baseURI = "https://automationexercise.com/api";

        RequestSpecification httpReq = RestAssured.given()
                                        .contentType(ContentType.URLENC) // important
                                        .formParam("search_product", "top");

        Response res = httpReq.request(Method.POST, "/searchProduct");

        // Print response
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        // Print status code
        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("-------------------------------------------------------------------------");
    }
    @Test
    public void postToSearchProductWithoutParam() {
    	
    	System.out.println("Search Product without search product parameter");
        RestAssured.baseURI = "https://automationexercise.com/api";

        // No formParam added intentionally
        RequestSpecification httpReq = RestAssured.given()
                                        .contentType(ContentType.URLENC);

        Response res = httpReq.request(Method.POST, "/searchProduct");

        // Print response
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        // Print status code
        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("-------------------------------------------------------------------------");
    }

}
