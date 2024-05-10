package com.safaricom.fairflowappmicroservice.dtos.Payment;

import lombok.Data;

@Data
public class PaymentRequestDto {
    private Long beneficiaryId;
    private Long representativeId;
    private double monetaryAmount;
}
