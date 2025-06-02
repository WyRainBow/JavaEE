package com.experimental.entity;

public class Order {
    private String orderId;
    private String itemName;
    private User user; // Nested POJO

    // Default constructor, getters, and setters are needed for Spring MVC binding

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", user=" + (user != null ? user.toString() : "null") +
                '}';
    }
} 