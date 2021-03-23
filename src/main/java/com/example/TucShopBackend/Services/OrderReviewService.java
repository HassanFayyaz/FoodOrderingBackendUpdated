package com.example.TucShopBackend.Services;

import com.example.TucShopBackend.Commons.ApiResponse;
import com.example.TucShopBackend.Commons.Status;
import com.example.TucShopBackend.DTO.OrderReviewDTO;
import com.example.TucShopBackend.Models.OrderReview;
import com.example.TucShopBackend.Models.Transactions;
import com.example.TucShopBackend.Repositories.OrderReviewRepository;
import com.example.TucShopBackend.Repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderReviewService {

    @Autowired
    OrderReviewRepository orderReviewRepository;
    @Autowired
    TransactionsRepository transactionsRepository;

    public ApiResponse postOrderReview(OrderReviewDTO orderReviewDTO){
        OrderReview orderReview = new OrderReview();
        orderReview.setComment(orderReviewDTO.getComment());
        orderReview.setReview(orderReviewDTO.getReview());

        Optional<Transactions> transactionFind = transactionsRepository.findById(orderReviewDTO.getTransaction_Id());
        Transactions transaction = transactionFind.get();
        transaction.setIsReview("R");
        orderReview.setTransactions(transaction);
        orderReviewRepository.save(orderReview);

        return new ApiResponse(Status.Status_Ok,"Success",orderReview);
    }

    public ApiResponse changeReviewStatus(Long id, Transactions transactionDto){
        Optional<Transactions> findTransaction = transactionsRepository.findById(id);
        if(findTransaction.isPresent()){
            Transactions transactions = findTransaction.get();
            transactions.setIsReview(transactionDto.getIsReview());
            transactionsRepository.save(transactions);
            return new ApiResponse(Status.Status_Ok,"Success",transactions);
        }
        else {
            return new ApiResponse(Status.Status_ERROR,"Not Found",null);
        }

    }


}