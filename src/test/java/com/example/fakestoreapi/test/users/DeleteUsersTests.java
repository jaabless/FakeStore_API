package com.example.fakestoreapi.test.users;


import com.example.fakestoreapi.base.BaseTest;
import com.example.fakestoreapi.config.Endpoints;
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
public class DeleteUsersTests extends BaseTest {
    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#validIds")
    @Story("DELETE Users")
    @DisplayName("Verify that user can be deleted successfully by ID")
    void testDeleteUser(int userId, int expectedStatus) {
        given()
                .spec(requestSpec)
                .when()
                .delete(Endpoints.USERS_DETAILS, userId)
                .then()
                .statusCode(expectedStatus)
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"))
                .header("Content-Type", containsString("application/json"));
    }
}