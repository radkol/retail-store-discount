package com.retailstore.discounts.service.discount.processor.percent;

import com.retailstore.discounts.service.discount.DiscountProcessorContext;

import java.math.BigDecimal;
import java.util.function.Predicate;

public class PercentageDiscountEvaluator {

    private final int percentage;
    private final Predicate<DiscountProcessorContext> rule;

    public PercentageDiscountEvaluator(int percentage, Predicate<DiscountProcessorContext> rule) {
        this.percentage = percentage;
        this.rule = rule;
    }

    public BigDecimal calculate(DiscountProcessorContext context) {
        if (!isApplicable(context)) {
            return BigDecimal.ZERO;
        }
        return context.getBill().multiply(BigDecimal.valueOf(percentage)).divide(BigDecimal.valueOf(100));
    }

    public boolean isApplicable(DiscountProcessorContext context) {
        return rule.test(context) && !context.isIncludeGroceries();
    }

    public double getPercentage() {
        return percentage;
    }
}