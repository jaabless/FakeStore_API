package com.example.fakestoreapi.test.users;

import com.example.fakestoreapi.base.BaseTest;
import com.example.fakestoreapi.config.Endpoints;
import com.example.fakestoreapi.model.Cart;
import com.example.fakestoreapi.model.User;
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

public class PutUsersTests extends BaseTest {

    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#validUpdateUserData")
    @Story("UPDATE Users")
    @DisplayName("Test Update /users/{id} endpoint with valid data")
    void testUpdateValidUser(User user, int expectedStatus) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .put(Endpoints.USERS_DETAILS,user.getId()) // Using ID 1 as example
                .then()
                .statusCode(expectedStatus)
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"))
                .header("Content-Type", containsString("application/json"));
    }

    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#invalidUpdateUserData")
    @Story("UPDATE Carts")
    @DisplayName("Test Update /users/{id} endpoint with invalid data")
    void testUpdateInvalidUser(User user, int expectedStatus) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .put(Endpoints.USERS_DETAILS,user.getId()) // Using ID 1 as example
                .then()
                .statusCode(expectedStatus)
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"))
                .header("Content-Type", containsString("application/json"));
    }
}