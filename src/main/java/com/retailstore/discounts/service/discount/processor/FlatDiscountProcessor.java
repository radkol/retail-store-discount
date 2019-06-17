package com.retailstore.discounts.service.discount.processor;

import com.retailstore.discounts.service.discount.DiscountProcessorContext;
import com.retailstore.discounts.service.discount.DiscountProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class FlatDiscountProcessor implements DiscountProcessor {

    private int STEP_DISCOUNT = 5;
    private int STEP_THRESHOLD = 100;

    @Override
    public BigDecimal calculate(DiscountProcessorContext context) {
        return BigDecimal.valueOf(context.getBill()
                .divide(BigDecimal.valueOf(STEP_THRESHOLD)).longValue() * STEP_DISCOUNT)
                .max(BigDecimal.ZERO);
    }
}
