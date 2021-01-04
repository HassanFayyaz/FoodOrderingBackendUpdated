package com.example.TucShopBackend.DTO;

public class RestaurantDTO {

    String restaurantName;
    String restaurantAddress;
    String restaurantType;
    String restaurantContactNumber;
    String restaurantEmail;
    Long userId;

    public RestaurantDTO() {
    }

    public RestaurantDTO(String restaurantName, String restaurantAddress, String restaurantType, String restaurantContactNumber, String restaurantEmail, Long userId) {
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantType = restaurantType;
        this.restaurantContactNumber = restaurantContactNumber;
        this.restaurantEmail = restaurantEmail;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
    }

    public String getRestaurantContactNumber() {
        return restaurantContactNumber;
    }

    public void setRestaurantContactNumber(String restaurantContactNumber) {
        this.restaurantContactNumber = restaurantContactNumber;
    }

    public String getRestaurantEmail() {
        return restaurantEmail;
    }

    public void setRestaurantEmail(String restaurantEmail) {
        this.restaurantEmail = restaurantEmail;
    }
}