package com.example.fakestoreapi.test.carts;


import com.example.fakestoreapi.base.BaseTest;
import com.example.fakestoreapi.config.Endpoints;
import com.example.fakestoreapi.model.Cart;
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
public class PostCartsTests extends BaseTest {

    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#validCartData")
    @Story("POST Cart")
    @DisplayName("Test POST carts with valid data")
    void testPostCartWithValidData(Cart cart, int expectedStatus) {
        given()
                .spec(requestSpec)
                .body(cart)
                .when()
                .post(Endpoints.CARTS)
                .then()
                .statusCode(expectedStatus)
                .body(matchesJsonSchemaInClasspath("schemas/cart-schema.json"))
                .header("Content-Type", containsString("application/json"))
                .body("id", notNullValue());
    }


    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#invalidCartData")
    @Story("POST Cart")
    @DisplayName("Test POST carts with invalid data")
    void testPostCartWithInvalidData(Cart cart, int expectedStatus) {
        given()
                .spec(requestSpec)
                .body(cart)
                .when()
                .post(Endpoints.CARTS)
                .then()
                .statusCode(expectedStatus)
                .body(matchesJsonSchemaInClasspath("schemas/cart-schema.json"))
                .header("Content-Type", containsString("application/json"))
                .body("id", notNullValue());
    }
}