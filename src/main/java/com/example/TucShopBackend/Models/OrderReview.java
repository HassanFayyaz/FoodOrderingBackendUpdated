package com.example.TucShopBackend.Models;


import org.jetbrains.annotations.Nullable;

import javax.persistence.*;


@Entity
public class OrderReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String review;
    @Nullable
    String comment;
    @OneToOne
    @JoinColumn(name = "transaction_id")
    Transactions transactions;

    public OrderReview() {
    }

    public OrderReview(String review, @Nullable String comment, Transactions transactions) {
        this.review = review;
        this.comment = comment;
        this.transactions = transactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }
}