package com.example.TucShopBackend.Repositories;

import com.example.TucShopBackend.Models.OrderReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderReviewRepository extends JpaRepository<OrderReview,Long> {
}