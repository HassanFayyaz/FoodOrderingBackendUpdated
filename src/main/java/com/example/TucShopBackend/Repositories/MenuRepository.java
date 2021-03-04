package com.example.TucShopBackend.Repositories;

import com.example.TucShopBackend.Models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
    @Query(value = "select * from menu where restaurant_id =:restaurant_id",nativeQuery = true)
    Optional<Menu> findByRestaurantId( @Param("restaurant_id") Long restaurant_id);
}