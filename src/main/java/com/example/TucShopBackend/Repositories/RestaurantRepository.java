package com.example.TucShopBackend.Repositories;

import com.example.TucShopBackend.Models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Long>{

    @Query(value = "Select * from restaurant where restaurant_name=:name",nativeQuery = true)
    public Restaurant getByRestaurantName(@Param("name") String name);
}