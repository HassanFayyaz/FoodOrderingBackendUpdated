package com.example.TucShopBackend.Controllers;


import com.cloudinary.Api;
import com.example.TucShopBackend.Commons.ApiResponse;
import com.example.TucShopBackend.DTO.RestaurantDTO;

import com.example.TucShopBackend.Models.Restaurant;
import com.example.TucShopBackend.Services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.TucShopBackend.Commons.ApiResponse;
import com.example.TucShopBackend.DTO.RestaurantDTO;
import com.example.TucShopBackend.Services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;


@CrossOrigin
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;



    @PostMapping("/")
    public ApiResponse addRestaurant(@RequestParam(value="restaurantImage",required = false)  MultipartFile restaurantImage,  RestaurantDTO restaurantDTO){
        restaurantDTO.setRestaurantImage(restaurantImage);

        return restaurantService.addRestaurant(restaurantDTO);
    }

    @GetMapping("/")
    public ApiResponse getRestaurantList(){

        return restaurantService.getRestaurantList();
    }

    @GetMapping("/{id}")
    public ApiResponse getRestaurantById(@PathVariable("id") Long id){
        return restaurantService.getRestaurantById(id);
    }

    @PutMapping("/{id}")
    public ApiResponse updateRestaurant(@PathVariable("id") Long id, @RequestBody RestaurantDTO restaurantDTO){
        return restaurantService.updateRestaurant(id,restaurantDTO);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteRestaurantById(@PathVariable("id") Long id){
        return restaurantService.deleteRestaurantById(id);
    }

    @RequestMapping(value = "/image/{restaurant}/{filename:.+}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> getImage(@PathVariable("filename") String filename, @PathVariable("restaurant") String restaurant)
            throws IOException {
        return restaurantService.getRestaurantImage(filename,restaurant);
    }
    @GetMapping("/inactive")
    public List<Restaurant> getInactiveRestaurants(){
        return restaurantService.getInactiveRestaurants();
    }
    @PutMapping("/active/{id}")
    public Restaurant updateRestaurants(@PathVariable("id") Long id){
        return restaurantService.updateRestaurantId(id);
    }
}
