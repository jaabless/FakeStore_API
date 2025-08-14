package com.example.fakestoreapi.test.products;


import com.example.fakestoreapi.base.BaseTest;
import com.example.fakestoreapi.config.Endpoints;
import com.example.fakestoreapi.data.StatusCodes;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@ExtendWith(AllureJunit5.class)
public class GetProductsTests extends BaseTest {


    @Test
    @Story("GET Products")
    @DisplayName("Test GET all products endpoint")
    void testGetAllProducts() {
        given()
                .spec(requestSpec)
                .when()
                .get(Endpoints.PRODUCTS)
                .then()
//                .spec(responseSpec)
                .statusCode(StatusCodes.OK)
                .body("size()", greaterThan(0))
                .header("Content-Type", containsString("application/json"))
                .body("id", notNullValue());
    }

    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#validIds")
    @Story("GET Products")
    @DisplayName("Test GET products by ID endpoint")
    void testGetProductById(int productId, int expectedStatus) {
        given()
                .spec(requestSpec)
                .pathParam("id", productId)
                .when()
                .get(Endpoints.PRODUCTS_DETAILS)
                .then()
                .statusCode(expectedStatus)
                .body(matchesJsonSchemaInClasspath("schemas/product-schema.json"))
                .header("Content-Type", containsString("application/json"))
                .body("id", equalTo(productId));
    }

    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#validIds")
    @Story("GET Product Response Time")
    @DisplayName("Test GET /products/{id} response time")
    void testGetProductResponseTime(int productId, int expectedStatus) {
        given()
                .spec(requestSpec)
                .pathParam("id", productId)
                .when()
                .get(Endpoints.PRODUCTS_DETAILS)
                .then()
                .time(lessThan(2000L)); // Response time < 2 seconds
    }
}