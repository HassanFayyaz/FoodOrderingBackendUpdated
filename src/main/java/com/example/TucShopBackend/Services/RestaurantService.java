package com.example.TucShopBackend.Services;

import com.cloudinary.Api;
import com.example.TucShopBackend.Commons.ApiResponse;
import com.example.TucShopBackend.Commons.Status;
import com.example.TucShopBackend.DTO.RestaurantDTO;
import com.example.TucShopBackend.Models.Menu;
import com.example.TucShopBackend.Models.Restaurant;
import com.example.TucShopBackend.Repositories.MenuRepository;
import com.example.TucShopBackend.Repositories.RestaurantRepository;
import com.example.TucShopBackend.Repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    UserDao userDao;


    public ApiResponse addRestaurant(RestaurantDTO restaurantDTO){
       Restaurant restaurantFind = restaurantRepository.getByRestaurantName(restaurantDTO.getRestaurantName());
        if(restaurantFind != null){
            return new ApiResponse(Status.Status_ERROR,"Restaurant Already Register by this name",null);
        }
        else{
            Restaurant restaurant = new Restaurant();

            restaurant.setRestaurantName(restaurantDTO.getRestaurantName());
            restaurant.setRestaurantAddress(restaurantDTO.getRestaurantAddress());
            restaurant.setRestaurantContactNumber(restaurantDTO.getRestaurantContactNumber());
            restaurant.setRestaurantEmail(restaurantDTO.getRestaurantEmail());
            restaurant.setRestaurantType(restaurantDTO.getRestaurantType());
            restaurant.setActive(true);
            restaurant.setUser(userDao.findById(restaurantDTO.getUserId()).get());
            restaurantRepository.save(restaurant);
            Menu menu = new Menu();
            menu.setRestaurant(restaurant);
            menu.setActive(true);
            menuRepository.save(menu);
            return new ApiResponse(Status.Status_Ok,"Success",restaurant);
        }
    }

    public ApiResponse getRestaurantList(){
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return new ApiResponse(Status.Status_Ok,"Success",restaurantList);
    }

    public ApiResponse getRestaurantById(Long id){

        Optional<Restaurant> restaurantFind = restaurantRepository.findById(id);
        if(restaurantFind!=null){
            Restaurant restaurant = restaurantFind.get();
            return new ApiResponse(Status.Status_Ok,"Successfully get",restaurant);
        }
        else {
            return new ApiResponse(Status.Status_Ok,"Not Found",null);
        }

    }

    public ApiResponse updateRestaurant(Long id,RestaurantDTO restaurantDTO){

        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isPresent()){
            Restaurant restaurantToUpdate = restaurant.get();

            restaurantToUpdate.setRestaurantName(restaurantDTO.getRestaurantName());
            restaurantToUpdate.setRestaurantType(restaurantDTO.getRestaurantType());
            restaurantToUpdate.setRestaurantContactNumber(restaurantDTO.getRestaurantContactNumber());
            restaurantToUpdate.setRestaurantAddress(restaurantDTO.getRestaurantAddress());
            restaurantToUpdate.setRestaurantEmail(restaurantDTO.getRestaurantEmail());
            restaurantToUpdate.setActive(true);
            restaurantRepository.save(restaurantToUpdate);

            return new ApiResponse(Status.Status_Ok,"Successfully Updated",restaurantToUpdate);
        }
        else {
            return  new ApiResponse(Status.Status_ERROR,"Some thing went Wrong",null);
        }

    }

    public ApiResponse deleteRestaurantById(Long id){

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        if(restaurantOptional!=null){
            Restaurant restaurant = restaurantOptional.get();
            restaurant.setActive(false);

            restaurantRepository.save(restaurant);
            return new ApiResponse(Status.Status_Ok,"Successfully Deleted",restaurant);
        }
        return new ApiResponse(Status.Status_ERROR,"Some thing Went Wrong",null);
    }


}