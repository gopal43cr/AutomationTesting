package java.com.apiTesting;
import static io.restassured.RestAssured.baseURI;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class ApiTesting {
    @BeforeClass
    public void setup() {
        baseURI = "https://automationexercise.com/api";
    }
    @Test
    public void getAllProductsList() {
    	
    	System.out.println("GET All Products List");
    	RequestSpecification httpReq = RestAssured.given();

        Response res = httpReq.request(Method.GET, "/productsList");

        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        // Optional validation
        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("-------------------------------------------------------------------------");
    }
    @Test
    public void postToProductsList() {
    	
    	System.out.println("PUT To Products List");
        RestAssured.baseURI = "https://automationexercise.com/api";

        // Create request
        RequestSpecification httpReq = RestAssured.given();

        // Send PUT request
        Response res = httpReq.request(Method.POST, "/productsList");

        // Print full response
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        // Validate status code
        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("-------------------------------------------------------------------------");
    }

    @Test
    public void getAllBrandsList() {

        // Set base URI once
    	System.out.println("GET All Brands List");
        RestAssured.baseURI = "https://automationexercise.com/api";

        RequestSpecification httpReq = RestAssured.given();

        Response res = httpReq.request(Method.GET, "/brandsList");
        
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        // Optional validation
        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("-------------------------------------------------------------------------");
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
    @Test
    public void postToVerifyLoginWithValidDetails() {
    	
    	System.out.println("Verify Login with Valid Details");
        RestAssured.baseURI = "https://automationexercise.com/api";

        RequestSpecification httpReq = RestAssured.given()
                                        .contentType(ContentType.URLENC)
                                        .formParam("email", "harshikasingh0312@gmail.com")
                                        .formParam("password", "Harshika26");

        Response res = httpReq.request(Method.POST, "/verifyLogin");

        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        // Print status code
        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("-------------------------------------------------------------------------");
    }
    @Test
    public void postToVerifyLoginWithoutEmail() {
    	
    	System.out.println("Verify Login without Email");
        RestAssured.baseURI = "https://automationexercise.com/api";

        // Only password is sent, email is missing
        RequestSpecification httpReq = RestAssured.given()
                                        .contentType(ContentType.URLENC)
                                        .formParam("password", "test123");

        Response res = httpReq.request(Method.POST, "/verifyLogin");

        // Print full response
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        // Print status code
        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("-------------------------------------------------------------------------");
    }
    @Test
    public void deleteVerifyLogin() {
    	
    	System.out.println("Delete to Verify Login");
        RestAssured.baseURI = "https://automationexercise.com/api";

        RequestSpecification httpReq = RestAssured.given();

        // Send DELETE request
        Response res = httpReq.request(Method.DELETE, "/verifyLogin");

        // Print full response
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        // Print status code
        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("-------------------------------------------------------------------------");
    }
    @Test
    public void postToVerifyLoginWithInvalidDetails() {

    	System.out.println(" Verify Login with invalid details");
        RestAssured.baseURI = "https://automationexercise.com/api";

        // Send invalid credentials
        RequestSpecification httpReq = RestAssured.given()
                                        .contentType(ContentType.URLENC)
                                        .formParam("email", "invalid@example.com")
                                        .formParam("password", "wrongpass");

        Response res = httpReq.request(Method.POST, "/verifyLogin");

        // Print full response
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        // Print status code
        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("-------------------------------------------------------------------------");
    }
    @Test
    public void postToCreateUserAccount() {

    	System.out.println("Create/Register User Account");
        RestAssured.baseURI = "https://automationexercise.com/api";

        RequestSpecification httpReq = RestAssured.given()
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
            .formParam("mobile_number", "9876543210");

        // Send POST request
        Response res = httpReq.request(Method.POST, "/createAccount");

        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        // Print status code
        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("-------------------------------------------------------------------------");
    }
    @Test
    public void deleteUserAccount() {
    	
    	System.out.println("Delete User Account");
        RestAssured.baseURI = "https://automationexercise.com/api";

        RequestSpecification httpReq = RestAssured.given()
            .contentType(ContentType.URLENC)
            .formParam("email", "johndoe123@example.com")
            .formParam("password", "Test@1234");

        // Send DELETE request
        Response res = httpReq.request(Method.DELETE, "/deleteAccount");

        // Print full response
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        // Print status code
        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("-------------------------------------------------------------------------");

    }
    @Test
    public void putToUpdateUserAccount() {
    	
    	System.out.println("Update User Account");
        RestAssured.baseURI = "https://automationexercise.com/api";

        RequestSpecification httpReq = RestAssured.given()
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
            .formParam("mobile_number", "9876543210");

        // Send PUT request
        Response res = httpReq.request(Method.PUT, "/updateAccount");

        // Print full response
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        // Print status code
        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("-------------------------------------------------------------------------");

    }
    @Test
    public void getUserDetailByEmail() {
    	
    	System.out.println("Get User Details By Email");
        RestAssured.baseURI = "https://automationexercise.com/api";

        RequestSpecification httpReq = RestAssured.given()
                                        .queryParam("email", "johndoe123@example.com"); // Email to search

        Response res = httpReq.request(Method.GET, "/getUserDetailByEmail");

        // Print full response
        String jsonPretty = res.jsonPath().prettify();
        System.out.println(jsonPretty);

        // Print status code
        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("-------------------------------------------------------------------------");

    }
}
