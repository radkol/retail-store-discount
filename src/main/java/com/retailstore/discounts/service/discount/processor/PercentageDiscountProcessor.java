package com.retailstore.discounts.service.discount.processor;

import com.retailstore.discounts.domain.Role;
import com.retailstore.discounts.service.discount.DiscountProcessorContext;
import com.retailstore.discounts.service.discount.DiscountProcessor;
import com.retailstore.discounts.service.discount.processor.percent.PercentageDiscountEvaluator;
import com.retailstore.discounts.util.DateTimeUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
class PercentageDiscountProcessor implements DiscountProcessor, InitializingBean {

    private final List<PercentageDiscountEvaluator> evaluators = new ArrayList<>();

    @Override
    public BigDecimal calculate(DiscountProcessorContext context) {
        return evaluators.stream().filter(evaluator -> evaluator.isApplicable(context))
                .findFirst()
                .map(evaluator -> evaluator.evaluate(context))
                .orElse(BigDecimal.ZERO);
    }

    /**
     * May be load these from outside, or load them with DSL from database or configuration.
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        evaluators.add(new PercentageDiscountEvaluator(30,
                discountProcessorContext -> discountProcessorContext.getUserDto().getRoles().contains(Role.EMPLOYEE.name())));
        evaluators.add(new PercentageDiscountEvaluator(10,
                discountProcessorContext -> discountProcessorContext.getUserDto().getRoles().contains(Role.AFFILIATE.name())));
        evaluators.add(new PercentageDiscountEvaluator(5,
                discountProcessorContext -> discountProcessorContext.getUserDto().getRoles().contains(Role.CUSTOMER.name())
                && DateTimeUtil.nowMinusYearsInUTC(2).isAfter(discountProcessorContext.getUserDto().getCreatedAt())));

        Collections.sort(evaluators, Comparator.comparing(PercentageDiscountEvaluator::getPercentage).reversed());
    }
}
