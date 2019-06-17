package com.retailstore.discounts.service.discount.processor;

import com.retailstore.discounts.domain.Role;
import com.retailstore.discounts.domain.User;
import com.retailstore.discounts.helpers.TestHelper;
import com.retailstore.discounts.service.discount.DiscountProcessor;
import com.retailstore.discounts.service.dto.BillDto;
import com.retailstore.discounts.service.dto.UserBillDto;
import com.retailstore.discounts.service.dto.UserDto;
import com.retailstore.discounts.util.DateTimeUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;

public class FlatDiscountProcessorUnitTest {

    private DiscountProcessor amountDiscountProcessor = new FlatDiscountProcessor();

    @Test
    public void noDiscountForNegativeAmount() {
        User regular_customer = TestHelper.createUser("Regular Customer", "customer@retail.com", TestHelper.COMMON_PASSWORD,
                Arrays.asList(Role.CUSTOMER), DateTimeUtil.nowInUTC());
        UserDto byUsername = new UserDto(regular_customer);
        BillDto billDto = TestHelper.createBillDto(BigDecimal.valueOf(-100), false);
        BigDecimal result = amountDiscountProcessor.calculate(new UserBillDto(byUsername, billDto));
        assertTrue(result.equals(BigDecimal.ZERO));
    }

    @Test
    public void noDiscountForAmountLessThan100() {
        User regular_customer = TestHelper.createUser("Regular Customer", "customer@retail.com", TestHelper.COMMON_PASSWORD,
                Arrays.asList(Role.CUSTOMER), DateTimeUtil.nowInUTC());
        UserDto byUsername = new UserDto(regular_customer);
        BillDto billDto = TestHelper.createBillDto(BigDecimal.valueOf(99), false);
        BigDecimal result = amountDiscountProcessor.calculate(new UserBillDto(byUsername, billDto));
        assertTrue(result.equals(BigDecimal.ZERO));
    }

    @Test
    public void discount5onSumMoreThan100() {
        User regular_customer = TestHelper.createUser("Regular Customer", "customer@retail.com", TestHelper.COMMON_PASSWORD,
                Arrays.asList(Role.CUSTOMER), DateTimeUtil.nowInUTC());
        UserDto byUsername = new UserDto(regular_customer);
        BillDto billDto = TestHelper.createBillDto(BigDecimal.valueOf(101), false);
        BigDecimal result = amountDiscountProcessor.calculate(new UserBillDto(byUsername, billDto));
        assertTrue(result.equals(BigDecimal.valueOf(5)));
    }

    @Test
    public void discount10onSumMoreThan200() {
        User regular_customer = TestHelper.createUser("Regular Customer", "customer@retail.com", TestHelper.COMMON_PASSWORD,
                Arrays.asList(Role.CUSTOMER), DateTimeUtil.nowInUTC());
        UserDto byUsername = new UserDto(regular_customer);
        BillDto billDto = TestHelper.createBillDto(BigDecimal.valueOf(250.50), false);
        BigDecimal result = amountDiscountProcessor.calculate(new UserBillDto(byUsername, billDto));
        assertTrue(result.equals(BigDecimal.valueOf(10)));
    }

    @Test
    public void discount45onSum990() {
        User regular_customer = TestHelper.createUser("Regular Customer", "customer@retail.com", TestHelper.COMMON_PASSWORD,
                Arrays.asList(Role.CUSTOMER), DateTimeUtil.nowInUTC());
        UserDto byUsername = new UserDto(regular_customer);
        BillDto billDto = TestHelper.createBillDto(BigDecimal.valueOf(990.90), false);
        BigDecimal result = amountDiscountProcessor.calculate(new UserBillDto(byUsername, billDto));
        assertTrue(result.equals(BigDecimal.valueOf(45)));
    }

}
