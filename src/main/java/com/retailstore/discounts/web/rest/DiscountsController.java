package com.retailstore.discounts.web.rest;

import com.retailstore.discounts.service.DiscountService;
import com.retailstore.discounts.service.dto.BillDto;
import com.retailstore.discounts.service.dto.DiscountedBillDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;

@RestController
@RequestMapping("/api/discounts")
public class DiscountsController {

    private final DiscountService discountService;

    public DiscountsController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping
    @ApiOperation(value = "Calculate discounted (net) amount of a bill, based on the user & amount")
    public ResponseEntity<DiscountedBillDto> getDiscountedBill(@RequestBody @Valid BillDto billDto, Principal principal) {
        DiscountedBillDto discountedBillDto = discountService.applyDiscount(principal, billDto);
        return new ResponseEntity<>(discountedBillDto, HttpStatus.OK);
    }
}
