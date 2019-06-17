package com.retailstore.discounts.service.discount;

import java.math.BigDecimal;

public interface DiscountProcessor {
    BigDecimal calculate(DiscountProcessorContext context);
}
