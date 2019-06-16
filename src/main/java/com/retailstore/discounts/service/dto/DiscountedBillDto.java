package com.retailstore.discounts.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DiscountedBillDto {
    private BigDecimal discountedBill;
    private BigDecimal bill;
}
