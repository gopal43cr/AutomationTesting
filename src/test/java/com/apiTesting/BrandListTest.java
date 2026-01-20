package com.apiTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BrandListTest {
	@Test
    public void getAllBrandsList() {

		// Base URI
        RestAssured.baseURI = "https://automationexercise.com/api";

        RequestSpecification httpReq = RestAssured.given();

        // Send GET request
        Response res = httpReq.request(Method.GET, "/brandsList");

        // Measure response time
        long responseTime = res.getTime();
        long maxAllowedTime = 5000; // 4 seconds

        // Fail immediately if response is too slow
        Assert.assertTrue(responseTime <= maxAllowedTime,
                "Response time exceeded! Took " + responseTime + " ms, allowed max: " + maxAllowedTime + " ms");

        // Only print if response is within allowed time
        if (responseTime <= maxAllowedTime) {
            System.out.println("GET All Brands List - Successful");
            System.out.println("Status Code: " + res.getStatusCode());
            System.out.println("Response Time: " + responseTime + " ms");
            System.out.println("Response Body:\n" + res.jsonPath().prettify());
            System.out.println("-------------------------------------------------------------------------");
        }
    }
        
    @Test
    public void putToAllBrandsList() {
    	
    	System.out.println("PUT to Brands List");
        // Base URI
        RestAssured.baseURI = "https://automationexercise.com/api";

        // Create request
        RequestSpecification httpReq = RestAssured.given();

        // Send PUT request
        Response res = httpReq.request(Method.PUT, "/brandsList");

        // Print full response
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);
        
        
        // Validate status code
        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("-------------------------------------------------------------------------");
    }
}
