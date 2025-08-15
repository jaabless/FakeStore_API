package com.example.fakestoreapi.data;

import com.example.fakestoreapi.model.AuthUser;
import com.example.fakestoreapi.model.Cart;
import com.example.fakestoreapi.model.Product;
import com.example.fakestoreapi.model.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Arrays;
import java.util.stream.Stream;


public class TestDataProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(1, 200), // Positive case: Valid ID
                Arguments.of(9999, 404), // Negative case: Non-existent ID
                Arguments.of(-1, 400) // Edge case: Invalid ID
        );
    }

    public static Stream<Arguments> validproductData() {
        return Stream.of(
                Arguments.of(new Product(0, "Test Product", 99.99, "Test Description", "electronics", "test.jpg"), 201),
                Arguments.of(new Product(0, "Another Product", 49.99, "Another Description", "books", "another.jpg"), 201), // Positive: Valid product
                Arguments.of(new Product(0, "Edge Case Product", 0.01, "Edge Case Description", "electronics", "edge.jpg"), 201) // Edge: Minimum price
        );
    }

    public static Stream<Arguments> validUpdateProductData() {
        return Stream.of(
                Arguments.of(new Product(1, "Product", 99.99, "Test Description", "electronics", "test.jpg"), 200),
                Arguments.of(new Product(2, "Product", 49.99, "Another Description", "books", "another.jpg"), 200), // Positive: Valid product
                Arguments.of(new Product(3, "Case Product", 0.01, "Edge Case Description", "electronics", "edge.jpg"), 200) // Edge: Minimum price
        );
    }

    public static Stream<Arguments> invalidproductData() {
        return Stream.of(
                Arguments.of(new Product(0, "Test Product", -99.99, "Test Description", "electronics", "test.jpg"), 400), // Negative: Invalid price
                Arguments.of(new Product(0, "Test Product", 99.99, "", "electronics", "test.jpg"), 400), // Negative: Empty description
                Arguments.of(new Product(0, "", 99.99, "Test Description", "electronics", "test.jpg"), 400), // Negative: Empty title
                Arguments.of(new Product(0, "x".repeat(256), 99.99, "Test Description", "electronics", "test.jpg"), 400) // Edge: Title too long
        );
    }

    public static Stream<Arguments> invalidUpdateProductData() {
        return Stream.of(
                Arguments.of(new Product(0, "Test Product", -99.99, "Test Description", "electronics", "test.jpg"), 400), // Negative: Invalid price
                Arguments.of(new Product(0, "Test Product", 99.99, "", "electronics", "test.jpg"), 400), // Negative: Empty description
                Arguments.of(new Product(0, "", 99.99, "Test Description", "electronics", "test.jpg"), 400), // Negative: Empty title
                Arguments.of(new Product(0, "x".repeat(256), 99.99, "Test Description", "electronics", "test.jpg"), 400) // Edge: Title too long
        );
    }

    public static Stream<Arguments> validUserData() {
        return Stream.of(
                Arguments.of(
                        new User(
                                0,
                                "test@example.com",
                                "testuser",
                                "password123",
                                new User.Name("Test", "User"),
                                new User.Address(
                                        "123 Street",
                                        "City",
                                        "12345",
                                        new User.Geo("0.0", "0.0"),
                                        "1234567890"
                                )
                        ),
                        201
                ),
                // Positive: Valid user
                Arguments.of(
                        new User(
                                0,
                                "sample@example.com",
                                "testuser",
                                "password123",
                                new User.Name("Test", "User"),
                                new User.Address(
                                        "123 Street",
                                        "City",
                                        "12345",
                                        new User.Geo("0.0", "0.0"),
                                        "1234567890"
                                )
                        ),
                        201
                )
        );
    }

    public static Stream<Arguments> validUpdateUserData() {
        return Stream.of(
                Arguments.of(
                        new User(
                                0,
                                "test@example.com",
                                "testuser",
                                "password123",
                                new User.Name("Test", "User"),
                                new User.Address(
                                        "123 Street",
                                        "City",
                                        "12345",
                                        new User.Geo("0.0", "0.0"),
                                        "1234567890"
                                )
                        ),
                        200
                ),
                // Positive: Valid user
                Arguments.of(
                        new User(
                                0,
                                "sample@example.com",
                                "testuser",
                                "password123",
                                new User.Name("Test", "User"),
                                new User.Address(
                                        "123 Street",
                                        "City",
                                        "12345",
                                        new User.Geo("0.0", "0.0"),
                                        "1234567890"
                                )
                        ),
                        200
                )
        );
    }

    public static Stream<Arguments> invalidUserData() {
        return Stream.of(
                Arguments.of(
                        new User(
                                0,
                                "invalid-email",
                                "testuser",
                                "password123",
                                new User.Name("Test", "User"),
                                new User.Address(
                                        "123 Street",
                                        "City",
                                        "12345",
                                        new User.Geo("0.0", "0.0"),
                                        "1234567890"
                                )
                        ),
                        400
                ), // Negative: Invalid email

                Arguments.of(
                        new User(
                                0,
                                "test@example.com",
                                "",
                                "password123",
                                new User.Name("Test", "User"),
                                new User.Address(
                                        "123 Street",
                                        "City",
                                        "12345",
                                        new User.Geo("0.0", "0.0"),
                                        "1234567890"
                                )
                        ),
                        400
                )
        );
    }

    public static Stream<Arguments> invalidUpdateUserData() {
        return Stream.of(
                Arguments.of(
                        new User(
                                0,
                                "invalid-email",
                                "testuser",
                                "password123",
                                new User.Name("Test", "User"),
                                new User.Address(
                                        "123 Street",
                                        "City",
                                        "12345",
                                        new User.Geo("0.0", "0.0"),
                                        "1234567890"
                                )
                        ),
                        400
                ), // Negative: Invalid email

                Arguments.of(
                        new User(
                                0,
                                "test@example.com",
                                "",
                                "password123",
                                new User.Name("Test", "User"),
                                new User.Address(
                                        "123 Street",
                                        "City",
                                        "12345",
                                        new User.Geo("0.0", "0.0"),
                                        "1234567890"
                                )
                        ),
                        400
                )
        );
    }



    public static Stream<Arguments> validCartData() {
        return Stream.of(
                Arguments.of(new Cart(0, 1, "2023-10-01", Arrays.asList(new Cart.ProductItem(1, 2))), 201),
                Arguments.of(new Cart(0, 2, "2023-10-02", Arrays.asList(new Cart.ProductItem(2, 1))), 201), // Valid cart with one item
                Arguments.of(new Cart(0, 3, "2023-10-03", Arrays.asList(new Cart.ProductItem(3, 5))), 201) // Valid cart with multiple items
        );
    }

    public static Stream<Arguments> validUpdateCartData() {
        return Stream.of(
                Arguments.of(new Cart(1, 1, "2023-10-01", Arrays.asList(new Cart.ProductItem(1, 3))), 200),
                Arguments.of(new Cart(2, 2, "2023-10-02", Arrays.asList(new Cart.ProductItem(2, 13))), 200), // Valid cart with one item
                Arguments.of(new Cart(3, 3, "2023-10-03", Arrays.asList(new Cart.ProductItem(3, 53))), 200) // Valid cart with multiple items
        );
    }
    public static Stream<Arguments> invalidCartData() {
        return Stream.of(
                Arguments.of(new Cart(0, 1, "2023-10-01", Arrays.asList(new Cart.ProductItem(9999, 2))), 404), // Non-existent product
                Arguments.of(new Cart(0, 2, "2023-10-02", Arrays.asList(new Cart.ProductItem(-1, 1))), 400), // Invalid product ID
                Arguments.of(new Cart(0, 3, "2023-10-03", Arrays.asList(new Cart.ProductItem(3, -5))), 400) // Invalid quantity
        );
    }

    public static Stream<Arguments> invalidUpdateCartData() {
        return Stream.of(
                Arguments.of(new Cart(0, 1, "2023-10-01", Arrays.asList(new Cart.ProductItem(9999, 2))), 404), // Non-existent product
                Arguments.of(new Cart(0, 2, "2023-10-02", Arrays.asList(new Cart.ProductItem(-1, 1))), 400), // Invalid product ID
                Arguments.of(new Cart(0, 3, "2023-10-03", Arrays.asList(new Cart.ProductItem(3, -5))), 400) // Invalid quantity
        );
    }


    public static Stream<Arguments> validIds() {
        return Stream.of(
                Arguments.of(1, 200), // Valid user ID
                Arguments.of(2, 200), // Another valid user ID
                Arguments.of(3, 200) // Yet another valid user ID
        );
    }

    public static Stream<Arguments> invalidIds() {
        return Stream.of(
                Arguments.of(9999, 404), // Non-existent user ID
                Arguments.of(-1, 400), // Invalid user ID (negative)
                Arguments.of(0, 400) // Invalid user ID (zero)
        );
    }

    public static Stream<Arguments> validAuthUserData() {
        return Stream.of(
        Arguments.of(
                new AuthUser(
                        "test@example.com",
                        "password123"
                ),
                200
            ),
                Arguments.of(
                        new AuthUser(
                                "sample@example.com",
                                "password123"
                        ),
                        200
                )
        );
    }

    public static Stream<Arguments> invalidAuthUserData() {
        return Stream.of(
                Arguments.of(
                        new AuthUser(
                                "invalid-email",
                                "password123"
                        ),
                        400
                ), // Negative: Invalid email

                Arguments.of(
                        new AuthUser(
                                "invalid-email",
                                "invalid-password"
                        ),
                        400
                )
        );

    }


}