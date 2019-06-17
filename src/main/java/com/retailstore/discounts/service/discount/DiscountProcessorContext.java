package com.retailstore.discounts.service.discount;

import com.retailstore.discounts.service.dto.UserDto;

import java.math.BigDecimal;

public interface DiscountProcessorContext {
    boolean isIncludeGroceries();
    BigDecimal getBill();
    UserDto getUserDto();
}
