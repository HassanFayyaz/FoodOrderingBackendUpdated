package com.example.TucShopBackend.DTO;

import com.example.TucShopBackend.Models.Category;
import com.example.TucShopBackend.Models.Restaurant;

import java.util.List;

public class MenuDTO {

    Long restaurant_id;
    List<Category> category;

    public MenuDTO() {
    }

    public MenuDTO(Long restaurant_id, List<Category> category) {
        this.restaurant_id = restaurant_id;
        this.category = category;
    }

    public Long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }
}