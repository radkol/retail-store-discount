package com.retailstore.discounts.service.processors;

import com.retailstore.discounts.service.dto.UserBillDto;

import java.math.BigDecimal;

class AmountDiscountProcessor implements DiscountProcessor {

    public static final int STEP_DISCOUNT = 5;
    public static final int STEP_THRESHOLD = 100;

    @Override
    public BigDecimal calculateDiscount(UserBillDto context) {
        return BigDecimal.valueOf(context.getBillDto().getBill()
                .divide(BigDecimal.valueOf(STEP_THRESHOLD)).longValue() * STEP_DISCOUNT)
                .max(BigDecimal.ZERO);
    }
}
