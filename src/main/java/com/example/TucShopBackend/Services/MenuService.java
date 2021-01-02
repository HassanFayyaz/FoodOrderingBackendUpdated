package com.example.TucShopBackend.Services;

import com.example.TucShopBackend.Commons.ApiResponse;
import com.example.TucShopBackend.Commons.Status;
import com.example.TucShopBackend.DTO.MenuDTO;
import com.example.TucShopBackend.Models.Menu;
import com.example.TucShopBackend.Models.Restaurant;
import com.example.TucShopBackend.Repositories.MenuRepository;
import com.example.TucShopBackend.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    public ApiResponse postMenu(MenuDTO menuDTO){
        Optional<Restaurant> findRestaurant = restaurantRepository.findById(menuDTO.getRestaurant_id());

        if(findRestaurant != null){
            Restaurant restaurant = findRestaurant.get();
            Menu menu = new Menu();
            menu.setRestaurant(restaurant);
            menu.setActive(true);
            menuRepository.save(menu);
            return new ApiResponse(Status.Status_Ok,"Successfully Saved",menu);
        }
        else{
            return new ApiResponse(Status.Status_ERROR,"Something Went Wrong",null);
        }

    }

    public ApiResponse getMenuList(){
        List<Menu> menuList = menuRepository.findAll();
        return new ApiResponse(Status.Status_Ok,"Menu List",menuList);
    }

    public ApiResponse getMenuById(Long id){
        Optional<Menu> menuOptional = menuRepository.findById(id);
        if(menuOptional!=null){
            Menu menu = menuOptional.get();
            return new ApiResponse(Status.Status_Ok,"Success",menu);
        }
        else{
            return new ApiResponse(Status.Status_ERROR,"Not Found",null);
        }
    }

    public ApiResponse updateMenu(Long id, MenuDTO menuDTO){
        Optional<Menu> menuOptional = menuRepository.findById(id);
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(menuDTO.getRestaurant_id());
        if(menuOptional!= null && restaurantOptional!=null){
            Menu menuToUpdate =  menuOptional.get();
            Restaurant restaurant = restaurantOptional.get();
            menuToUpdate.setRestaurant(restaurant);
            menuToUpdate.setCategory(menuDTO.getCategory());
            menuRepository.save(menuToUpdate);
            return new ApiResponse(Status.Status_Ok,"Successfully Updated", menuToUpdate );
        }
        else{
            return new ApiResponse(Status.Status_ERROR,"Not Found",null);
        }
    }

    public ApiResponse deleteMenuById(Long id){
        Optional<Menu> menuOptional = menuRepository.findById(id);
        if(menuOptional != null){
            Menu menu = menuOptional.get();
            menu.setActive(false);
            menuRepository.save(menu);
            return new ApiResponse(Status.Status_Ok,"Successfully Deleted", menu);
        }
        else{
            return new ApiResponse(Status.Status_ERROR,"Not Found", null);

        }
    }


}
