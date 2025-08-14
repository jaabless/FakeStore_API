package com.example.fakestoreapi.test.products;

import com.example.fakestoreapi.base.BaseTest;
import com.example.fakestoreapi.config.Endpoints;
import com.example.fakestoreapi.model.Product;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class PutProductsTests extends BaseTest {


    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#validUpdateProductData")
    @Story("PUT Product")
    @DisplayName("Test PUT /products/{id} endpoint with valid data")
    void testPutValidProduct(Product product,int expectedStatus) {
        given()
                .spec(requestSpec)
                .body(product)
                .when()
                .put(Endpoints.PRODUCTS_DETAILS, product.getId())
                .then()
                .body(matchesJsonSchemaInClasspath("schemas/product-schema.json"))
                .header("Content-Type", containsString("application/json"))
                .body("id", equalTo( product.getId()));

    }

    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#invalidUpdateProductData")
    @Story("PUT Product")
    @DisplayName("Test PUT /products endpoint with invalid data")
    void testPutInvalidProduct(Product product,int expectedStatus) {
        given()
                .spec(requestSpec)
                .body(product)
                .when()
                .put(Endpoints.PRODUCTS_DETAILS, product.getId())
                .then()
                .statusCode(expectedStatus)
                .body(matchesJsonSchemaInClasspath("schemas/product-schema.json"))
                .header("Content-Type", containsString("application/json"))
                .body("id", equalTo( product.getId()));

    }
}
