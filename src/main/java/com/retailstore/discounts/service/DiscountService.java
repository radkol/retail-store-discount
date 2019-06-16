package com.retailstore.discounts.service;

import com.retailstore.discounts.service.dto.BillDto;
import com.retailstore.discounts.service.dto.UserBillDto;
import com.retailstore.discounts.service.dto.DiscountedBillDto;
import com.retailstore.discounts.service.dto.UserDto;
import com.retailstore.discounts.service.processors.DiscountProcessor;
import com.retailstore.discounts.service.processors.DiscountProcessorFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountService implements InitializingBean {

    private final UserService userService;
    private final List<DiscountProcessor> discountProcessors;
    private final DiscountProcessorFactory discountProcessorFactory;

    public DiscountService(UserService userService, DiscountProcessorFactory discountProcessorFactory) {
        this.userService = userService;
        this.discountProcessorFactory = discountProcessorFactory;
        discountProcessors = new ArrayList<>(2);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for(DiscountProcessorFactory.DiscountProcessorType value: DiscountProcessorFactory.DiscountProcessorType.values()) {
            discountProcessors.add(discountProcessorFactory.get(value));
        }
    }

    public DiscountedBillDto applyDiscount(Principal principal, BillDto billDto) {

        UserDto userDto = userService.findByUsername(principal.getName());

        UserBillDto userBillDto = new UserBillDto(userDto, billDto);

        BigDecimal totalDiscount = BigDecimal.ZERO;
        for(DiscountProcessor discountProcessor : discountProcessors) {
            totalDiscount = totalDiscount.add(discountProcessor.calculateDiscount(userBillDto));
        }

        DiscountedBillDto discountedBillDto = new DiscountedBillDto();
        discountedBillDto.setBill(billDto.getBill());
        discountedBillDto.setDiscountedBill(billDto.getBill().subtract(totalDiscount));

        return discountedBillDto;


    }

}
