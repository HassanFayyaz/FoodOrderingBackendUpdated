package com.example.TucShopBackend.Services;

import com.example.TucShopBackend.DTO.AddressDTO;
import com.example.TucShopBackend.Models.Address;
import com.example.TucShopBackend.Repositories.AddressRepository;
import com.example.TucShopBackend.Repositories.UserDao;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserDao userDao;


    Long now =System.currentTimeMillis();


    public Address getAddressById(Long id)
    {
      return  addressRepository.findById(id).get();
    }

    public Address  saveAddress(AddressDTO addressDTO) {

        Address address= new Address();
        address.setDeliveryAddress(addressDTO.getDeliveryAddress());
        address.setBillingAddress(addressDTO.getBillingAddress());
        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        address.setState(addressDTO.getState());
        address.setCreatedBy(1);
        address.setUpdatedBy(1);
        address.setPhone(addressDTO.getPhone());
        address.setFullName(addressDTO.getFullName());
        address.setCreatedDate(new Timestamp(now));
        address.setLastUpdated(new Timestamp(now));

        address.setUser(userDao.findById(addressDTO.getUserId()).get());
            return addressRepository.save(address);
    }
}
