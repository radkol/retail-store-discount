package com.retailstore.discounts.service.discount.processor;

import com.retailstore.discounts.domain.Role;
import com.retailstore.discounts.domain.User;
import com.retailstore.discounts.helpers.TestHelper;
import com.retailstore.discounts.service.dto.BillDto;
import com.retailstore.discounts.service.dto.UserBillDto;
import com.retailstore.discounts.service.dto.UserDto;
import com.retailstore.discounts.util.DateTimeUtil;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;

public class PercentageDiscountProcessorUnitTest {

    private PercentageDiscountProcessor percentageDiscountProcessor = new PercentageDiscountProcessor();

    @Before
    public void init() throws Exception {
        percentageDiscountProcessor.afterPropertiesSet();
    }

    @Test
    public void noPercentageDiscountForRegularCustomer() {
        User regular_customer = TestHelper.createUser("Regular Customer", "customer@retail.com", TestHelper.COMMON_PASSWORD,
                Arrays.asList(Role.CUSTOMER), DateTimeUtil.nowInUTC());
        UserDto userDto = new UserDto(regular_customer);
        BillDto billDto = TestHelper.createBillDto(BigDecimal.valueOf(200), false);
        BigDecimal result = percentageDiscountProcessor.calculate(new UserBillDto(userDto, billDto));
        assertTrue(result.equals(BigDecimal.ZERO));
    }

    @Test
    public void noPercentageDiscountForBillWithGroceries() {
        User regular_customer = TestHelper.createUser("Regular Customer", "employee@retail.com", TestHelper.COMMON_PASSWORD,
                Arrays.asList(Role.EMPLOYEE, Role.CUSTOMER), DateTimeUtil.nowInUTC());
        UserDto userDto = new UserDto(regular_customer);
        BillDto billDto = TestHelper.createBillDto(BigDecimal.valueOf(200), true);
        BigDecimal result = percentageDiscountProcessor.calculate(new UserBillDto(userDto, billDto));
        assertTrue(result.equals(BigDecimal.ZERO));
    }

    @Test
    public void percentageDiscount30ForEmployeeCustomer() {
        User regular_customer = TestHelper.createUser("Employee User", "employee@retail.com", TestHelper.COMMON_PASSWORD,
                Arrays.asList(Role.EMPLOYEE, Role.CUSTOMER), DateTimeUtil.nowInUTC());
        UserDto userDto = new UserDto(regular_customer);
        BillDto billDto = TestHelper.createBillDto(BigDecimal.valueOf(200), false);
        BigDecimal result = percentageDiscountProcessor.calculate(new UserBillDto(userDto, billDto));
        assertTrue(result.equals(BigDecimal.valueOf(60)));
    }

    @Test
    public void percentageDiscount10ForAffiliateCustomer() {
        User regular_customer = TestHelper.createUser("Affiliate User", "affiliate@retail.com", TestHelper.COMMON_PASSWORD,
                Arrays.asList(Role.AFFILIATE, Role.CUSTOMER), DateTimeUtil.nowInUTC());
        UserDto userDto = new UserDto(regular_customer);
        BillDto billDto = TestHelper.createBillDto(BigDecimal.valueOf(200), false);
        BigDecimal result = percentageDiscountProcessor.calculate(new UserBillDto(userDto, billDto));
        assertTrue(result.equals(BigDecimal.valueOf(20)));
    }

    @Test
    public void percentageDiscount30ForLongTermEmployee() {
        User regular_customer = TestHelper.createUser("Long Term Employee", "employee-longterm@retail.com", TestHelper.COMMON_PASSWORD,
                Arrays.asList(Role.EMPLOYEE, Role.CUSTOMER), DateTimeUtil.nowMinusYearsInUTC(4));
        UserDto userDto = new UserDto(regular_customer);
        BillDto billDto = TestHelper.createBillDto(BigDecimal.valueOf(200), false);
        BigDecimal result = percentageDiscountProcessor.calculate(new UserBillDto(userDto, billDto));
        assertTrue(result.equals(BigDecimal.valueOf(60)));
    }

    @Test
    public void percentageDiscount5ForLongTermCustomer() {
        User regular_customer = TestHelper.createUser("Long Term Customer", "customer-longterm@retail.com", TestHelper.COMMON_PASSWORD,
                Arrays.asList(Role.CUSTOMER), DateTimeUtil.nowMinusYearsInUTC(4));
        UserDto userDto = new UserDto(regular_customer);
        BillDto billDto = TestHelper.createBillDto(BigDecimal.valueOf(200), false);
        BigDecimal result = percentageDiscountProcessor.calculate(new UserBillDto(userDto, billDto));
        assertTrue(result.equals(BigDecimal.valueOf(10)));
    }


}
