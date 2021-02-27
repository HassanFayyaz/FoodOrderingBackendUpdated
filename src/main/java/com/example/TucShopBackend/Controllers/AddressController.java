package com.example.TucShopBackend.Controllers;

import com.example.TucShopBackend.DTO.AddressDTO;
import com.example.TucShopBackend.Models.Address;
import com.example.TucShopBackend.Services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable("id") Long id){

        return addressService.getAddressById(id);
    }

    @PostMapping("/")
    public Address saveAddress(@RequestBody AddressDTO addressDTO)
    {
        return addressService.saveAddress(addressDTO);
    }
}
