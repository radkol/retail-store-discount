package com.retailstore.discounts.service.processors;

import com.retailstore.discounts.service.dto.BillDto;
import com.retailstore.discounts.service.dto.DiscountProcessorDto;
import com.retailstore.discounts.service.dto.UserDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

public interface DiscountProcessor {
    BigDecimal calculateDiscount(DiscountProcessorDto source);
}
