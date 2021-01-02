package com.example.TucShopBackend.Controllers;

import com.example.TucShopBackend.Commons.ApiResponse;
import com.example.TucShopBackend.DTO.MenuDTO;
import com.example.TucShopBackend.Services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @PostMapping("/")
    public ApiResponse postMenu(@RequestBody MenuDTO menuDTO){
        return menuService.postMenu(menuDTO);
    }

    @GetMapping("/")
    public ApiResponse getMenuList(){
        return menuService.getMenuList();
    }

    @GetMapping("/{id}")
    public ApiResponse getMenuById(@PathVariable("id") Long id){
        return menuService.getMenuById(id);
    }

    @PutMapping("/{id}")
    public ApiResponse updateMenu(@PathVariable("id") Long id, @RequestBody MenuDTO menuDTO){
        return menuService.updateMenu(id,menuDTO);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteMenuById(@PathVariable("id") Long id){
        return menuService.deleteMenuById(id);
    }
}