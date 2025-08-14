package com.example.fakestoreapi.test.users;

import com.example.fakestoreapi.base.BaseTest;
import com.example.fakestoreapi.config.Endpoints;
import com.example.fakestoreapi.data.StatusCodes;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

@ExtendWith(AllureJunit5.class)
public class GetUsersTests extends BaseTest {

    @Test
    @Story("GET Users")
    @DisplayName("Test GET /users endpoint")
    void testGetAllUsers() {
        given()
                .spec(requestSpec)
                .when()
                .get(Endpoints.USERS)
                .then()
//                .spec(responseSpec)
                .statusCode(StatusCodes.OK)
                .body("size()", greaterThan(0))
                .body(matchesJsonSchemaInClasspath("schemas/users-array-schema.json"))
                .header("Content-Type", containsString("application/json"));
    }

    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#validIds")
    @Story("GET Users")
    @DisplayName("Test GET /users/{id} endpoint")
    void testGetUserByValidId(int userId, int expectedStatus) {
        given()
                .spec(requestSpec)
//                .pathParam("id", userId)
                .when()
                .get(Endpoints.USERS_DETAILS, userId)
                .then()
                .statusCode(expectedStatus)
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"))
                .header("Content-Type", containsString("application/json"))
                .body("id", equalTo(userId));
    }


    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#invalidIds")
    @Story("GET Users")
    @DisplayName("Test GET /users/{id} endpoint with invalid ID")
    void testGetUserByInvalidId(int userId, int expectedStatus) {
        given()
                .spec(requestSpec)
                .pathParam("id", userId)
                .when()
                .get(Endpoints.USERS_DETAILS)
                .then()
                .statusCode(expectedStatus)
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"))
                .header("Content-Type", containsString("application/json"))
                .body("id", equalTo(userId));
    }


}