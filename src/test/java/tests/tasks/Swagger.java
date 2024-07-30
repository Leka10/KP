package tests.tasks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class Swagger  {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void testFindPetsByStatusAvailable() {
        Response response = RestAssured
                .given()
                .queryParam("status", "available")
                .when()
                .get("/pet/findByStatus")
                .then()
                .statusCode(200)
                .body("status", Matchers.everyItem(equalTo("available")))
                .log()
                .all()
                .extract()
                .response();

        Assert.assertNotNull(response.jsonPath().getList("$"));
    }

    @Test
    public void testFindPetsByStatusPending() {
        Response response = RestAssured
                .given()
                .queryParam("status", "pending")
                .when()
                .get("/pet/findByStatus")
                .then()
                .statusCode(200)
                .body("status", Matchers.everyItem(equalTo("pending")))
                .log()
                .all()
                .extract()
                .response();

        Assert.assertNotNull(response.jsonPath().getList("$"));
    }

    @Test
    public void testFindPetsByStatusSold() {
        Response response = RestAssured
                .given()
                .queryParam("status", "sold")
                .when()
                .get("/pet/findByStatus")
                .then()
                .statusCode(200)
                .body("status", Matchers.everyItem(equalTo("sold")))
                .log()
                .all()
                .extract()
                .response();

        Assert.assertNotNull(response.jsonPath().getList("$"));
    }

    @Test
    public void testFindPetsByStatusInvalid() {
        Response response = RestAssured
                .given()
                .queryParam("status", "invalidstatusvalue")
                .when()
                .get("/pet/findByStatus")
                .then()
                .statusCode(400)
                .extract()
                .response();

        Assert.assertTrue(response.jsonPath().getString("message").contains("Invalid status value"));
    }
}
