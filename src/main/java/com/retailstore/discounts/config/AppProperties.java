package com.retailstore.discounts.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Getter
@Setter
public class AppProperties {

    private final AppProperties.Jwt jwt = new AppProperties.Jwt();
    private final AppProperties.Discount discount = new AppProperties.Discount();

    public static class Discount {
        private int discountFlatThreshold;
        private int discountFlatStep;

        public int getDiscountFlatThreshold() {
            return discountFlatThreshold;
        }

        public void setDiscountFlatThreshold(int discountFlatThreshold) {
            this.discountFlatThreshold = discountFlatThreshold;
        }

        public int getDiscountFlatStep() {
            return discountFlatStep;
        }

        public void setDiscountFlatStep(int discountFlatStep) {
            this.discountFlatStep = discountFlatStep;
        }
    }

    public static class Jwt {
        private String tokenSecret;
        private int tokenValidity;
        public String getTokenSecret() {
            return tokenSecret;
        }
        public void setTokenSecret(String tokenSecret) {
            this.tokenSecret = tokenSecret;
        }
        public int getTokenValidity() {
            return tokenValidity;
        }
        public void setTokenValidity(int tokenValidity) {
            this.tokenValidity = tokenValidity;
        }

    }

}
