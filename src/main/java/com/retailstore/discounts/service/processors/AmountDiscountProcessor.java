package com.retailstore.discounts.service.processors;

import com.retailstore.discounts.service.dto.DiscountProcessorDto;

import java.math.BigDecimal;
import java.util.Objects;

class AmountDiscountProcessor implements DiscountProcessor {

    public static final int STEP_DISCOUNT = 5;
    public static final int STEP_THRESHOLD = 100;

    @Override
    public BigDecimal calculateDiscount(DiscountProcessorDto context) {
        Objects.requireNonNull(context);
        Objects.requireNonNull(context.getBillDto());
        return BigDecimal.valueOf(context.getBillDto().getBill()
                .divide(BigDecimal.valueOf(STEP_THRESHOLD)).longValue() * STEP_DISCOUNT)
                .max(BigDecimal.ZERO);
    }
}
