package com.example.fakestoreapi.test.auth;

import com.example.fakestoreapi.base.BaseTest;
import com.example.fakestoreapi.config.Endpoints;
import com.example.fakestoreapi.data.StatusCodes;
import com.example.fakestoreapi.model.Cart;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;

public class AuthUser extends BaseTest {


//    @ParameterizedTest
//    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#validAuthUserData")
    @Test
    @Story("POST Login")
    @DisplayName("Test POST login with valid data")
    void testLoginWithValidCredentials() {
        String body = "{\"username\":\"mor_2314\",\"password\":\"83r5^_\"}";
        given()
                .spec(requestSpec)
                .body(body)
                .when()
                .post(Endpoints.AUTH)
                .then()
                .statusCode(StatusCodes.OK)
                .header("Content-Type", containsString("application/json"))
                .body("token", notNullValue());
    }


}
