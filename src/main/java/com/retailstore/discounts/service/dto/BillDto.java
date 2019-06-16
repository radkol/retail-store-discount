package com.retailstore.discounts.service.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
public class BillDto {

    @Min(0)
    @NotNull
    private BigDecimal bill;

    private boolean includeGroceries;

}
