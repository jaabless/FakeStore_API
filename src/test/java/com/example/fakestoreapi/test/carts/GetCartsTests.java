package com.example.fakestoreapi.test.carts;


import com.example.fakestoreapi.base.BaseTest;
import com.example.fakestoreapi.config.Endpoints;
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


@ExtendWith(AllureJunit5.class)
public class GetCartsTests extends BaseTest {
    @Test
    @Story("GET Carts")
    @DisplayName("Verify that the GET /carts endpoint returns a list of carts")
    @Description("Test GET /carts endpoint")
    void testGetAllCarts() {
        given()
                .spec(requestSpec)
                .when()
                .get(Endpoints.CARTS)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .header("Content-Type", containsString("application/json"));
    }

    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#validIds")
    @Story("GET Carts")
    @DisplayName("Test GET cart by ID endpoint")
    void testGetCartById(int cartId, int expectedStatus) {
        given()
                .spec(requestSpec)
                .pathParam("id", cartId)
                .when()
                .get(Endpoints.CARTS_DETAILS)
                .then()
                .statusCode(expectedStatus)
                .header("Content-Type", containsString("application/json"))
                .body(matchesJsonSchemaInClasspath("schemas/cart-schema.json"))
                .body("id", equalTo(cartId));

    }


    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#invalidIds")
    @Story("GET Carts")
    @DisplayName("Test GET /carts/{id} endpoint with invalid ID")
    void testGetCartByInvalidId(int cartId, int expectedStatus) {
        given()
                .spec(requestSpec)
                .pathParam("id", cartId)
                .when()
                .get(Endpoints.CARTS_DETAILS)
                .then()
                .statusCode(expectedStatus)
                .header("Content-Type", containsString("application/json"))
                .body(matchesJsonSchemaInClasspath("schemas/cart-schema.json"))
                .body("id", equalTo(cartId));
        System.out.println(expectedStatus);

    }


}