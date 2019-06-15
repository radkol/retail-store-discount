package com.retailstore.discounts.service.processors;

import com.retailstore.discounts.domain.Role;
import com.retailstore.discounts.service.dto.DiscountProcessorDto;
import com.retailstore.discounts.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;

class PercentageDiscountProcessor implements DiscountProcessor {

    private final Logger log = LoggerFactory.getLogger(PercentageDiscountProcessor.class);

    private static final Map<Integer, Predicate<DiscountProcessorDto>> discountRules;

    static {
        Map<Integer, Predicate<DiscountProcessorDto>> percentToRuleMapping = new TreeMap<>(Comparator.reverseOrder());
        percentToRuleMapping.put(30, source -> source.getUserDto().getRoles().contains(Role.EMPLOYEE.name()));
        percentToRuleMapping.put(10, source -> source.getUserDto().getRoles().contains(Role.AFFILIATE.name()));
        percentToRuleMapping.put(5, source -> source.getUserDto().getRoles().contains(Role.CUSTOMER.name())
                && DateTimeUtil.nowMinusYearsInUTC(2).isAfter(source.getUserDto().getCreatedAt()));
        discountRules = Collections.unmodifiableMap(percentToRuleMapping);
    }

    @Override
    public BigDecimal calculateDiscount(DiscountProcessorDto source) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(source.getBillDto());
        Objects.requireNonNull(source.getUserDto());

        if (source.getBillDto().isIncludeGroceries()) {
            log.info("Percentage discount do not apply on bill that includes groceries ");
            return BigDecimal.ZERO;
        }

        Optional<Integer> percentage = discountRules.entrySet().stream()
                .filter(entry -> entry.getValue().test(source))
                .map(entry -> entry.getKey())
                .findFirst();

        if(!percentage.isPresent()) {
            log.info("No applicable percentage discount found for " + source);
            return BigDecimal.ZERO;

        }

        return source.getBillDto().getBill().multiply(BigDecimal.valueOf(percentage.get())).divide(BigDecimal.valueOf(100));

    }
}
