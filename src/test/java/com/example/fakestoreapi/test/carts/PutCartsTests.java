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
public class PutCartsTests extends BaseTest {

    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#validUpdateCartData")
    @Story("UPDATE Carts")
    @DisplayName("Test Update /carts/{id} endpoint with valid data")
    void testPutValidCart(Cart cart, int expectedStatus) {
        given()
                .spec(requestSpec)
                .body(cart)
                .when()
                .put(Endpoints.CARTS_DETAILS,cart.getId()) // Using ID 1 as example
                .then()
                .statusCode(expectedStatus)
                .body(matchesJsonSchemaInClasspath("schemas/cart-schema.json"))
                .header("Content-Type", containsString("application/json"))
                .body("id", notNullValue());
    }

    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#invalidUpdateCartData")
    @Story("UPDATE Carts")
    @DisplayName("Test update /carts/{id} endpoint with invalid data")
    void testPutInvalidCart(Cart cart, int expectedStatus) {
        given()
                .spec(requestSpec)
                .body(cart)
                .when()
                .put(Endpoints.CARTS_DETAILS,cart.getId()) // Using ID 1 as example
                .then()
                .statusCode(expectedStatus)
                .body(matchesJsonSchemaInClasspath("schemas/cart-schema.json"))
                .header("Content-Type", containsString("application/json"))
                .body("id", notNullValue());
    }

}