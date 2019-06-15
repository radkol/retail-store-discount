package com.retailstore.discounts.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public class AppProperties {

    private final AppProperties.Jwt jwt = new AppProperties.Jwt();

    public Jwt getJwt() {
        return jwt;
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
