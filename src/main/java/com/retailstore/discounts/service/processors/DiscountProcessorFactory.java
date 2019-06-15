package com.retailstore.discounts.service.processors;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DiscountProcessorFactory {

    public enum DiscountProcessorType {
        PERCENTAGE, AMOUNT;
    }

    public DiscountProcessor get(DiscountProcessorType type) {
        Objects.requireNonNull(type);
        switch (type) {
            case PERCENTAGE: return new PercentageDiscountProcessor();
            case AMOUNT: return new AmountDiscountProcessor();
            default: throw new IllegalArgumentException("cannot find processor for [type] " + type);
        }
    }
}

