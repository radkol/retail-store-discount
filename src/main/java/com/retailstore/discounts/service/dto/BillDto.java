package com.retailstore.discounts.service.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


public class BillDto {

    @Min(0)
    @NotNull
    private BigDecimal bill;

    private boolean includeGroceries;

    public BigDecimal getBill() {
        return bill;
    }

    public void setBill(BigDecimal bill) {
        this.bill = bill;
    }

    public boolean isIncludeGroceries() {
        return includeGroceries;
    }

    public void setIncludeGroceries(boolean includeGroceries) {
        this.includeGroceries = includeGroceries;
    }

    @Override
    public String toString() {
        return "BillDto{" +
                "bill=" + bill +
                ", includeGroceries=" + includeGroceries +
                '}';
    }
}
