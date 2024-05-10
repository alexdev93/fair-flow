package com.safaricom.fairflowtransactionsmicroservice;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long beneficiaryId;
    private Long representativeId;

    private double monetaryAmount;

    @CreationTimestamp
    private Date createdAt;



}
