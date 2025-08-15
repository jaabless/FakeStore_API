package com.example.fakestoreapi.test.products;

import com.example.fakestoreapi.base.BaseTest;
import com.example.fakestoreapi.config.Endpoints;
import com.example.fakestoreapi.data.StatusCodes;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class DeleteProductsTests extends BaseTest {

    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#validIds")
    @Story("DELETE Product")
    @DisplayName("Verify that user can delete a product by valid ID")
    void testDeleteProductById(int productId, int expectedStatus) {
        given()
                .spec(requestSpec)
                .pathParam("id", productId)
                .when()
                .delete(Endpoints.PRODUCTS_DETAILS)
                .then()
                .statusCode(expectedStatus)
                .body(matchesJsonSchemaInClasspath("schemas/product-schema.json"))
                .header("Content-Type", containsString("application/json"));
    }

    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#invalidIds")
    @Story("DELETE Product")
    @DisplayName("Verify that user cannot delete a product by invalid ID")
    void testDeleteProductByInvalidId(int productId, int expectedStatus) {
        given()
                .spec(requestSpec)
                .pathParam("id", productId)
                .when()
                .delete(Endpoints.PRODUCTS_DETAILS)
                .then()
                .statusCode(expectedStatus)
                .body(matchesJsonSchemaInClasspath("schemas/product-schema.json"))
                .header("Content-Type", containsString("application/json"));
    }
}
