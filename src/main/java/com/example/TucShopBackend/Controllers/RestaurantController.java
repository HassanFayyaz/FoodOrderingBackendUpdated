package com.example.TucShopBackend.Controllers;

import com.cloudinary.Api;
import com.example.TucShopBackend.Commons.ApiResponse;
import com.example.TucShopBackend.DTO.RestaurantDTO;
import com.example.TucShopBackend.Models.Restaurant;
import com.example.TucShopBackend.Services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/")
    public ApiResponse addRestaurant(@RequestBody RestaurantDTO restaurantDTO){
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
}