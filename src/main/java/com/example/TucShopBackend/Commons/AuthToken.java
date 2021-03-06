package com.example.TucShopBackend.Commons;

public class AuthToken {

    private String token;
    private String username;
    private String userType;
    private String email;
    private String accountAccessKey;
    private Boolean active;
    private Long id;
    private Long restaurantId;

    public AuthToken(String token, String username, String userType ) {
        this.token = token;
        this.username = username;
        this.userType = userType;
    }

    public AuthToken(String token, Long id, String username, String userType, String email, String accountAccessKey, Boolean active) {
        this.token = token;
        this.username = username;
        this.userType = userType;
        this.email = email;
        this.accountAccessKey = accountAccessKey;
        this.active = active;
        this.id= id;



    }

    public AuthToken(String token, String username, String userType, String email, String accountAccessKey, Boolean active, Long id, Long restaurantId) {
        this.token = token;
        this.username = username;
        this.userType = userType;
        this.email = email;
        this.accountAccessKey = accountAccessKey;
        this.active = active;
        this.id = id;
        this.restaurantId = restaurantId;
    }
    //    public AuthToken(String token, String username, String userType, String email, String accountAccessKey) {
//        this.token = token;
//        this.username = username;
//        this.userType = userType;
//        this.email = email;
//        this.accountAccessKey = accountAccessKey;
//
//    }



    public AuthToken(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public AuthToken(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountAccessKey() {
        return accountAccessKey;
    }

    public void setAccountAccessKey(String accountAccessKey) {
        this.accountAccessKey = accountAccessKey;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
