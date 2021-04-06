package com.example.TucShopBackend.Controllers;

import com.example.TucShopBackend.Commons.ApiResponse;
import com.example.TucShopBackend.DTO.OrderReviewDTO;
import com.example.TucShopBackend.Models.Transactions;
import com.example.TucShopBackend.Services.OrderReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/review")
public class OrderReviewController {


    @Autowired
    OrderReviewService orderReviewService;

    @PostMapping("/")
    public ApiResponse postOrderReview(@RequestBody OrderReviewDTO orderReviewDTO){
        return orderReviewService.postOrderReview(orderReviewDTO);
    }

    @PatchMapping("/{id}")
    public ApiResponse changeReviewStatus(@PathVariable("id") Long id, @RequestBody Transactions transactions){
        return orderReviewService.changeReviewStatus(id,transactions);
    }

    @GetMapping("/restaurants/{restaurantId}")
    public ApiResponse getBestRestaurant(@PathVariable("restaurantId") String restaurantId){
        return orderReviewService.getBestRestaurant(restaurantId);
    }
}

