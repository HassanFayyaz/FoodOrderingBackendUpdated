package com.example.TucShopBackend.DTO;

import com.example.TucShopBackend.Models.Transactions;
import org.jetbrains.annotations.Nullable;

public class OrderReviewDTO {

    String review;
    @Nullable
    String comment;
    Long transaction_Id;
    Long restaurant_Id;

    public OrderReviewDTO() {

    }

    public OrderReviewDTO(String review, @Nullable String comment, Long transaction_Id, Long restaurant_Id) {
        this.review = review;
        this.comment = comment;
        this.transaction_Id = transaction_Id;
        this.restaurant_Id = restaurant_Id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getTransaction_Id() {
        return transaction_Id;
    }

    public void setTransaction_Id(Long transaction_Id) {
        this.transaction_Id = transaction_Id;
    }

    public Long getRestaurant_Id() {
        return restaurant_Id;
    }

    public void setRestaurant_Id(Long restaurant_Id) {
        this.restaurant_Id = restaurant_Id;
    }
}