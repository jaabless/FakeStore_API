package com.example.fakestoreapi.test.products;


import com.example.fakestoreapi.base.BaseTest;
import com.example.fakestoreapi.config.Endpoints;
import com.example.fakestoreapi.model.Product;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@ExtendWith(AllureJunit5.class)
public class PostProductsTests extends BaseTest {


    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#validproductData")
    @Story("POST Product")
    @DisplayName("Test POST /products endpoint with valid data")
    void testPostProduct(Product product, int expectedStatus) {
        given()
                .spec(requestSpec)
                .body(product)
                .when()
                .post(Endpoints.PRODUCTS)
                .then()
                .statusCode(expectedStatus)
                .body(matchesJsonSchemaInClasspath("schemas/product-schema.json"))
                .header("Content-Type", containsString("application/json"))
                .body("id", notNullValue());

    }

    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#invalidproductData")
    @Story("POST Product")
    @DisplayName("Test POST /products endpoint with invalid data")
    void testPostProductWithInvalidData(Product product, int expectedStatus) {
        given()
                .spec(requestSpec)
                .body(product)
                .when()
                .post(Endpoints.PRODUCTS)
                .then()
                .statusCode(expectedStatus)
                .body(matchesJsonSchemaInClasspath("schemas/product-schema.json"))
                .header("Content-Type", containsString("application/json"))
                .body("id", notNullValue());
    }
}