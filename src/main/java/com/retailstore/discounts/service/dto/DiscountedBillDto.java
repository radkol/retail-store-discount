package com.retailstore.discounts.service.dto;

import java.math.BigDecimal;

public class DiscountedBillDto {

    private BigDecimal discountedBill;
    private BigDecimal bill;

    public BigDecimal getDiscountedBill() {
        return discountedBill;
    }

    public void setDiscountedBill(BigDecimal discountedBill) {
        this.discountedBill = discountedBill;
    }

    public BigDecimal getBill() {
        return bill;
    }

    public void setBill(BigDecimal bill) {
        this.bill = bill;
    }
}
