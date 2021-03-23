package com.example.TucShopBackend.DTO;

public class NotReviewDTO {

    Long id;
    String createdBy;
    String isReview;


    public NotReviewDTO() {
    }

    public NotReviewDTO(Long id, String createdBy, String isReview) {
        this.id = id;
        this.createdBy = createdBy;
        this.isReview = isReview;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getIsReview() {
        return isReview;
    }

    public void setIsReview(String isReview) {
        this.isReview = isReview;
    }




}