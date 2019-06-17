package com.retailstore.discounts.service.discount.processor;

import com.retailstore.discounts.config.AppProperties;
import com.retailstore.discounts.service.discount.DiscountProcessor;
import com.retailstore.discounts.service.discount.DiscountProcessorContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class FlatDiscountProcessor implements DiscountProcessor {

    private AppProperties props;

    public FlatDiscountProcessor(AppProperties props) {
        this.props = props;
    }

    @Override
    public BigDecimal calculate(DiscountProcessorContext context) {
        return BigDecimal.valueOf(context.getBill()
                .divide(BigDecimal.valueOf(props.getDiscount().getDiscountFlatThreshold())).longValue() *
                props.getDiscount().getDiscountFlatStep())
                .max(BigDecimal.ZERO);
    }
}
