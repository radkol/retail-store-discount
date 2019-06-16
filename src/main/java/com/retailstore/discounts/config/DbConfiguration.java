package com.retailstore.discounts.config;

import com.retailstore.discounts.BootApp;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackageClasses = {BootApp.class})
public class DbConfiguration {
}
