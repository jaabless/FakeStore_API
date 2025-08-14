package com.example.fakestoreapi.model;


import java.util.List;

public class Cart {
    private int id;
    private int userId;
    private String date;
    private List<ProductItem> products;

    public Cart() {}

    public Cart(int id, int userId, String date, List<ProductItem> products) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.products = products;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public List<ProductItem> getProducts() { return products; }
    public void setProducts(List<ProductItem> products) { this.products = products; }

    public static class ProductItem {
        private int productId;
        private int quantity;

        public ProductItem() {}

        public ProductItem(int productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

        public int getProductId() { return productId; }
        public void setProductId(int productId) { this.productId = productId; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }
}