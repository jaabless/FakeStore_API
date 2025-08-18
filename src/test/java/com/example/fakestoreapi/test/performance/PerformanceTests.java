package com.example.fakestoreapi.test.performance;

import com.example.fakestoreapi.base.BaseTest;
import com.example.fakestoreapi.config.Endpoints;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@ExtendWith(AllureJunit5.class)
public class PerformanceTests extends BaseTest {


    @Test
    @Description("Test concurrent requests")
    @Story("Perfomance Testing")
    void testConcurrentRequests() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CloseableHttpClient httpClient = HttpClients.createDefault();

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                try {
                    httpClient.execute(new HttpGet("https://fakestoreapi.com/products/1"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        httpClient.close();
    }

    @Test
    @Description("Test rate limiting")
    @Story("Perfomance Testing")
    void testRateLimiting() {
        for (int i = 0; i < 100; i++) {
            given()
                    .spec(requestSpec)
                    .when()
                    .get("/products/1")
                    .then()
                    .statusCode(not(equalTo(429))) // Ensure no rate limiting
                    .time(lessThan(2000L));
        }
    }

    @ParameterizedTest
    @MethodSource("com.example.fakestoreapi.data.TestDataProvider#validIds")
    @Description("Test response time for GET /users/{id}")
    @Story("Perfomance Testing")
    void testGetUserResponseTime(int userId, int expectedStatus) {
        given()
                .spec(requestSpec)
                .when()
                .get(Endpoints.USERS_DETAILS, userId)
                .then()
                .time(lessThan(2000L)); // Response time < 2 seconds
    }
}