package com.example.fakestoreapi.test.carts;


import com.example.fakestoreapi.base.BaseTest;
import com.example.fakestoreapi.config.Endpoints;
import com.example.fakestoreapi.data.StatusCodes;
import io.qameta.allure.Description;
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

public class DeleteCartsTests extends BaseTest {


    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#validIds")
    @Story("DELETE Carts")
    @DisplayName("Test DELETE carts by ID with valid IDs")
    void testDeleteCartsByValidId(int cartId, int expectedStatus) {
        given()
                .spec(requestSpec)
                .when()
                .delete(Endpoints.CARTS_DETAILS, cartId)
                .then()
                .statusCode(expectedStatus)
                .body(matchesJsonSchemaInClasspath("schemas/cart-schema.json"))
                .header("Content-Type", containsString("application/json"));
    }

    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#invalidIds")
    @Story("DELETE Carts")
    @DisplayName("Test DELETE carts by ID with various invalid IDs")
    void testDeleteCartsByInvalidId(int cartId, int expectedStatus) {
        given()
                .spec(requestSpec)
                .when()
                .delete(Endpoints.CARTS_DETAILS, cartId)
                .then()
                .statusCode(expectedStatus)
                .body(matchesJsonSchemaInClasspath("schemas/cart-schema.json"))
                .header("Content-Type", containsString("application/json"));
    }
}