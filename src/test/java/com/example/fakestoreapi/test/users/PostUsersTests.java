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


@ExtendWith(AllureJunit5.class)
public class PostUsersTests extends BaseTest {

    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#validUserData")
    @Story("POST User")
    @DisplayName("Test POST user with valid data")
    void testPostUserWithValidData(User user, int expectedStatus) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post(Endpoints.USERS)
                .then()
                .statusCode(expectedStatus)
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"))
                .header("Content-Type", containsString("application/json"))
                .body("id", notNullValue());
    }

    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#invalidUserData")
    @Story("POST User")
    @DisplayName("Test POST user with valid data")
    void testPostUserWithInvalidData(User user, int expectedStatus) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post(Endpoints.USERS)
                .then()
                .statusCode(expectedStatus)
                .header("Content-Type", containsString("application/json"))
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"))
                .body("id", notNullValue());

    }
}