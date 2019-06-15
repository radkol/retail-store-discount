package com.retailstore.discounts.helpers;

import com.retailstore.discounts.domain.Role;
import com.retailstore.discounts.domain.User;
import com.retailstore.discounts.service.dto.BillDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.Instant;
import java.util.List;

public class TestHelper {

    public static final String ADMIN_ACC = "admin@retail.com";
    public static final String EMPLOYEE_ACC = "employee@retail.com";
    public static final String AFFILIATE_ACC = "affiliate@retail.com";
    public static final String CUSTOMER_ACC = "customer@retail.com";

    public static final String EMPLOYEE_LONGTERM_ACC = "employee-longterm@retail.com";
    public static final String AFFILIATE_LONGTERM_ACC = "affiliate-longterm@retail.com";
    public static final String CUSTOMER_LONGTERM_ACC = "customer-longterm@retail.com";
    public static final String COMMON_PASSWORD = "secret";


    public static Principal createPrincipal(String username, String password, List<GrantedAuthority> authorities) {
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    public static User createUser(String name, String email, String password, List<Role> roles, Instant createdAt) {
        User user = new User();
        user.setCreatedAt(createdAt);
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setRoles(roles);
        return user;
    }

    public static BillDto createBillDto(BigDecimal bill, boolean hasGroceries) {
        BillDto billDto = new BillDto();
        billDto.setBill(bill);
        billDto.setIncludeGroceries(hasGroceries);
        return billDto;
    }

}
