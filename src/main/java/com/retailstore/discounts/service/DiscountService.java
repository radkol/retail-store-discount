package com.retailstore.discounts.service;

import com.retailstore.discounts.service.discount.DiscountProcessor;
import com.retailstore.discounts.service.discount.DiscountProcessorContext;
import com.retailstore.discounts.service.dto.BillDto;
import com.retailstore.discounts.service.dto.DiscountedBillDto;
import com.retailstore.discounts.service.dto.UserBillDto;
import com.retailstore.discounts.service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Service
public class DiscountService {

    private final UserService userService;
    private final List<DiscountProcessor> discountProcessors;

    public DiscountService(UserService userService, List<DiscountProcessor> discountProcessors) {
        this.userService = userService;
        this.discountProcessors = discountProcessors;
    }

    public DiscountedBillDto applyDiscount(Principal principal, BillDto billDto) {

        UserDto userDto = userService.findByUsername(principal.getName());
        DiscountProcessorContext discountProcessorContext = new UserBillDto(userDto, billDto);
        BigDecimal discount = discountProcessors.stream()
                .map(processor -> processor.calculate(discountProcessorContext))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return DiscountedBillDto.builder()
                .bill(billDto.getBill())
                .discountedBill(billDto.getBill().subtract(discount))
                .build();

    }

}
