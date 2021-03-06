package com.example.TucShopBackend.DTO;

import com.example.TucShopBackend.Models.Product;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CategoryDTO {

    @NotNull(message = "Name cannot be null")
    String name;
//    Long parentID;
//    Long childID;
    MultipartFile image;

    List<Product> productList;

    Long restaurant_id;

    public CategoryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

//    public Long getParentID() {
//        return parentID;
//    }
//
//    public void setParentID(Long parentID) {
//        this.parentID = parentID;
//    }

//    public Long getMenu_id() {
//        return menu_id;
//    }
//
//    public void setMenu_id(Long menu_id) {
//        this.menu_id = menu_id;
//    }


    public Long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
}
