package com.apiTesting;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import io.restassured.response.Response;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class OperationsOnAccountTest {

    // API 11: POST To Create/Register User Account
    @Test
    public void postToCreateUserAccount() {
        RestAssured.given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.URLENC)
                .formParam("name", "John Doe")
                .formParam("email", "johndoe123@example.com")
                .formParam("password", "Test@1234")
                .formParam("title", "Mr")
                .formParam("birth_date", "15")
                .formParam("birth_month", "August")
                .formParam("birth_year", "1990")
                .formParam("firstname", "John")
                .formParam("lastname", "Doe")
                .formParam("company", "ABC Corp")
                .formParam("address1", "123 Main Street")
                .formParam("address2", "Apartment 4B")
                .formParam("country", "India")
                .formParam("zipcode", "700001")
                .formParam("state", "West Bengal")
                .formParam("city", "Kolkata")
                .formParam("mobile_number", "9876543210")
        .when()
                .post("/createAccount")
        .then()
                .statusCode(200)
                .body(notNullValue()); // message: User created!
    }

    // API 12: DELETE To Delete User Account
    @Test
    public void deleteUserAccount() {
        RestAssured.given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.URLENC)
                .formParam("email", "johndoe123@example.com")
                .formParam("password", "Test@1234")
        .when()
                .delete("/deleteAccount")
        .then()
                .statusCode(200)
                .body(notNullValue()); // message: Account deleted!
    }

    // API 13: PUT To Update User Account
    @Test
    public void putToUpdateUserAccount() {
        RestAssured.given()
                .baseUri("https://automationexercise.com/api")
                .contentType(ContentType.URLENC)
                .formParam("name", "John Doe Updated")
                .formParam("email", "johndoe123@example.com")
                .formParam("password", "Test@1234")
                .formParam("title", "Mr")
                .formParam("birth_date", "15")
                .formParam("birth_month", "August")
                .formParam("birth_year", "1990")
                .formParam("firstname", "John")
                .formParam("lastname", "Doe")
                .formParam("company", "ABC Corp")
                .formParam("address1", "123 Main Street")
                .formParam("address2", "Apartment 4B")
                .formParam("country", "India")
                .formParam("zipcode", "700001")
                .formParam("state", "West Bengal")
                .formParam("city", "Kolkata")
                .formParam("mobile_number", "9876543210")
        .when()
                .put("/updateAccount")
        .then()
                .statusCode(200)
                .body(notNullValue()) // message: User updated!
	     // Performance validation
	        .time(lessThan(1500L));
    }

    // API 14: GET User Detail By Email
    @Test
    public void getUserDetailByEmail() {
        RestAssured.given()
                .baseUri("https://automationexercise.com/api")
                .queryParam("email", "johndoe123@example.com")
        .when()
                .get("/getUserDetailByEmail")
        .then()
                .statusCode(200)
                .body(notNullValue()) // JSON: User Detail
	     // Performance validation
	        .time(lessThan(1500L));
    }
}
