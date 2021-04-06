package com.example.TucShopBackend.Repositories;

import com.example.TucShopBackend.Models.OrderReview;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderReviewRepository extends JpaRepository<OrderReview,Long> {

    @Query(value = "select sum(review) as BestRestaurant,restaurant_id from foodorderingdb.order_review group by restaurant_id=:restaurantId order by BestRestaurant asc",nativeQuery = true)
    public List<String> getBestRestaurantList(@Param("restaurantId") String restaurantId);
}