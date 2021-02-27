package com.example.TucShopBackend.DTO;

import com.example.TucShopBackend.Models.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

public class AddressDTO {

    private Long id;
    private String deliveryAddress;
    private String billingAddress;
    private String phone;
    private String city;
    private String state;
    private String country;
    private String fullName;
    private Integer createdBy;
    private Timestamp createdDate;
    private Integer updatedBy;
    private Timestamp lastUpdated;
    private Long userId;

    public AddressDTO() {
    }

    public AddressDTO(Long id, String deliveryAddress, String billingAddress, String phone, String city, String state, String country, String fullName, Integer createdBy, Timestamp createdDate, Integer updatedBy, Timestamp lastUpdated, Long userId) {
        this.id = id;
        this.deliveryAddress = deliveryAddress;
        this.billingAddress = billingAddress;
        this.phone = phone;
        this.city = city;
        this.state = state;
        this.country = country;
        this.fullName = fullName;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedBy = updatedBy;
        this.lastUpdated = lastUpdated;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeliveryAddress() { return deliveryAddress;}

    public void setDeliveryAddress(String deliveryAddress) {this.deliveryAddress = deliveryAddress; }

    public String getBillingAddress() { return billingAddress; }

    public void setBillingAddress(String billingAddress) { this.billingAddress = billingAddress; }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
