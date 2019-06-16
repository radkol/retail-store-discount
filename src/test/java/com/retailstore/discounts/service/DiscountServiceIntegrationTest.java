package com.retailstore.discounts.service;

import com.retailstore.discounts.domain.Role;
import com.retailstore.discounts.domain.User;
import com.retailstore.discounts.helpers.TestHelper;
import com.retailstore.discounts.service.dto.BillDto;
import com.retailstore.discounts.service.dto.DiscountedBillDto;
import com.retailstore.discounts.util.DateTimeUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DiscountServiceIntegrationTest {


	@Autowired DiscountService discountService;

	@Test
	public void applyOnlyPercentageDiscount() {
		DiscountedBillDto discountedBillDto = discountService.applyDiscount(
				TestHelper.createPrincipal(TestHelper.EMPLOYEE_ACC, TestHelper.COMMON_PASSWORD, null),
				TestHelper.createBillDto(BigDecimal.valueOf(50.50), false));
		Assert.assertEquals(discountedBillDto.getBill(), BigDecimal.valueOf(50.50));
		Assert.assertEquals(discountedBillDto.getDiscountedBill(), BigDecimal.valueOf(35.35)); // 15.15 discount
	}

	@Test
	public void applyPercentageAndAmountDiscount() {
		DiscountedBillDto discountedBillDto = discountService.applyDiscount(
				TestHelper.createPrincipal(TestHelper.EMPLOYEE_ACC, TestHelper.COMMON_PASSWORD, null),
				TestHelper.createBillDto(BigDecimal.valueOf(150), false));
		Assert.assertEquals(discountedBillDto.getBill(), BigDecimal.valueOf(150));
		Assert.assertEquals(discountedBillDto.getDiscountedBill(), BigDecimal.valueOf(100)); // 45 + 5 discount
	}

	@Test
	public void applyPercentageAndAmountDiscountAffiliate() {
		DiscountedBillDto discountedBillDto = discountService.applyDiscount(
				TestHelper.createPrincipal(TestHelper.AFFILIATE_ACC, TestHelper.COMMON_PASSWORD, null),
				TestHelper.createBillDto(BigDecimal.valueOf(150), false));
		Assert.assertEquals(discountedBillDto.getBill(), BigDecimal.valueOf(150));
		Assert.assertEquals(discountedBillDto.getDiscountedBill(), BigDecimal.valueOf(130)); // 15 + 5 discount
	}


	@Test
	public void applyOnlyAmountDiscount() {
		DiscountedBillDto discountedBillDto = discountService.applyDiscount(
				TestHelper.createPrincipal(TestHelper.CUSTOMER_ACC, TestHelper.COMMON_PASSWORD, null),
				TestHelper.createBillDto(BigDecimal.valueOf(150), false));
		Assert.assertEquals(discountedBillDto.getBill(), BigDecimal.valueOf(150));
		Assert.assertEquals(discountedBillDto.getDiscountedBill(), BigDecimal.valueOf(145)); // 5 discount
	}

	@Test
	public void applyLongTermDiscount() {
		DiscountedBillDto discountedBillDto = discountService.applyDiscount(
				TestHelper.createPrincipal(TestHelper.CUSTOMER_LONGTERM_ACC, TestHelper.COMMON_PASSWORD, null),
				TestHelper.createBillDto(BigDecimal.valueOf(150), false));
		Assert.assertEquals(discountedBillDto.getBill(), BigDecimal.valueOf(150));
		Assert.assertEquals(discountedBillDto.getDiscountedBill(), BigDecimal.valueOf(137.5)); // 5% + 5$ discount
	}

	@Test
	public void applyOnlyLongTermDiscount() {
		DiscountedBillDto discountedBillDto = discountService.applyDiscount(TestHelper.createPrincipal(
				TestHelper.CUSTOMER_LONGTERM_ACC, TestHelper.COMMON_PASSWORD, null),
				TestHelper.createBillDto(BigDecimal.valueOf(50), false));
		Assert.assertEquals(discountedBillDto.getBill(), BigDecimal.valueOf(50));
		Assert.assertEquals(discountedBillDto.getDiscountedBill(), BigDecimal.valueOf(47.5)); // 5% discount
	}

	@Test
	public void applyOnlyEmployeeDiscount() {
		DiscountedBillDto discountedBillDto = discountService.applyDiscount(TestHelper.createPrincipal(
				TestHelper.EMPLOYEE_LONGTERM_ACC, TestHelper.COMMON_PASSWORD, null),
				TestHelper.createBillDto(BigDecimal.valueOf(50), false));
		Assert.assertEquals(discountedBillDto.getBill(), BigDecimal.valueOf(50));
		Assert.assertEquals(discountedBillDto.getDiscountedBill(), BigDecimal.valueOf(35)); // 30% discount
	}

}
