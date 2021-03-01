package com.example.TucShopBackend.Models;

import javax.persistence.*;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String restaurantName;
    String restaurantAddress;
    String restaurantType;
    String restaurantContactNumber;
    String restaurantEmail;
    String restaurantImage;
    boolean isActive;


    public Restaurant() {
    }


    public Restaurant(String restaurantName, String restaurantAddress, String restaurantType, String restaurantContactNumber, String restaurantEmail, String restaurantImage, boolean isActive) {
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantType = restaurantType;
        this.restaurantContactNumber = restaurantContactNumber;
        this.restaurantEmail = restaurantEmail;
        this.restaurantImage = restaurantImage;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(String restaurantImage) {
        this.restaurantImage = restaurantImage;
    }
}