package com.retailstore.discounts.service.processors;

import com.retailstore.discounts.service.dto.UserBillDto;

import java.math.BigDecimal;

public interface DiscountProcessor {
    BigDecimal calculateDiscount(UserBillDto context);
}
