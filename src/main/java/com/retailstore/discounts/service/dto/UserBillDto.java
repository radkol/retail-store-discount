package com.retailstore.discounts.service.dto;

import com.retailstore.discounts.service.discount.DiscountProcessorContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;


@Getter
@ToString
@AllArgsConstructor
public class UserBillDto implements DiscountProcessorContext {

    private UserDto userDto;
    private BillDto billDto;

    @Override
    public boolean isIncludeGroceries() {
        return billDto.isIncludeGroceries();
    }

    @Override
    public BigDecimal getBill() {
        return billDto.getBill();
    }
}
