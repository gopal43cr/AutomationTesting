package com.apiTesting;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OperationsOnAccountTest {

    String email = "johndoe123@example.com";
    String password = "Test@1234";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://automationexercise.com/api";
    }

    // ================= API 11 =================
    // POST - Create User Account
    @Test(priority = 1)
    public void createUserAccount() {

        Response res =
            given()
                .contentType(ContentType.URLENC)
                .formParam("name", "John Doe")
                .formParam("email", email)
                .formParam("password", password)
                .formParam("title", "Mr")
                .formParam("birth_date", "15")
                .formParam("birth_month", "09")
                .formParam("birth_year", "1990")
                .formParam("firstname", "John")
                .formParam("lastname", "Doe")
                .formParam("company", "Example Company")
                .formParam("address1", "123 Main Street")
                .formParam("address2", "Apt 4B")
                .formParam("country", "USA")
                .formParam("zipcode", "10001")
                .formParam("state", "NY")
                .formParam("city", "New York")
                .formParam("mobile_number", "9999999999")
            .when()
                .post("/createAccount");

        System.out.println("===== CREATE USER ACCOUNT =====");
        System.out.println(res.jsonPath().prettify());

        int responseCode = res.jsonPath().getInt("responseCode");

        // Pass if user created OR already exists
        assertTrue(
            responseCode == 201 || responseCode == 400,
            "Unexpected responseCode: " + responseCode
        );
    }

    // ================= API 14 =================
    // GET - User Details By Email
    @Test(priority = 2)
    public void getUserDetailByEmail() {

        Response res =
            given()
                .queryParam("email", email)
            .when()
                .get("/getUserDetailByEmail");

        System.out.println("===== GET USER DETAILS =====");
        System.out.println(res.jsonPath().prettify());

        int responseCode = res.jsonPath().getInt("responseCode");

        assertEquals(responseCode, 200);
        assertEquals(res.jsonPath().getString("user.email"), email);
    }

    // ================= API 13 =================
    // PUT - Update User Account
    @Test(priority = 3)
    public void updateUserAccount() {

        Response res =
            given()
                .contentType(ContentType.URLENC)
                .formParam("name", "John Doe Updated")
                .formParam("email", email)
                .formParam("password", password)
                .formParam("city", "Los Angeles")
            .when()
                .put("/updateAccount");

        System.out.println("===== UPDATE USER ACCOUNT =====");
        System.out.println(res.jsonPath().prettify());

        int responseCode = res.jsonPath().getInt("responseCode");

        // Pass if updated OR account not found
        assertTrue(
            responseCode == 200 || responseCode == 404,
            "Unexpected responseCode: " + responseCode
        );
    }

    // ================= API 12 =================
    // DELETE - User Account
    @Test(priority = 4)
    public void deleteUserAccount() {

        Response res =
            given()
                .contentType(ContentType.URLENC)
                .formParam("email", email)
                .formParam("password", password)
            .when()
                .delete("/deleteAccount");

        System.out.println("===== DELETE USER ACCOUNT =====");
        System.out.println(res.jsonPath().prettify());

        int responseCode = res.jsonPath().getInt("responseCode");

        // Pass if deleted OR already deleted
        assertTrue(
            responseCode == 200 || responseCode == 404,
            "Unexpected responseCode: " + responseCode
        );
    }
}
