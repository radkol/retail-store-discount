package com.retailstore.discounts;

import com.retailstore.discounts.domain.Role;
import com.retailstore.discounts.domain.User;
import com.retailstore.discounts.repository.UserRepository;
import com.retailstore.discounts.util.DateTimeUtil;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Component
public class LoadDemoData {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public LoadDemoData(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        loadUsers();
    }

    private void loadUsers() {
        final String secret = "secret";

        repository.save(loadUser("Regular Customer", "customer@retail.com", secret,
                Arrays.asList(Role.CUSTOMER), DateTimeUtil.nowInUTC()));
        repository.save(loadUser("Employee User", "employee@retail.com", secret,
                Arrays.asList(Role.EMPLOYEE, Role.CUSTOMER), DateTimeUtil.nowInUTC()));
        repository.save(loadUser("Affiliate User", "affiliate@retail.com", secret,
                Arrays.asList(Role.AFFILIATE, Role.CUSTOMER), DateTimeUtil.nowInUTC()));
        repository.save(loadUser("Long Term Customer", "customer-longterm@retail.com", secret,
                Arrays.asList(Role.CUSTOMER), DateTimeUtil.nowMinusYearsInUTC(3)));
        repository.save(loadUser("Long Term Employee", "employee-longterm@retail.com", secret,
                Arrays.asList(Role.EMPLOYEE, Role.CUSTOMER), DateTimeUtil.nowMinusYearsInUTC(4)));
        repository.save(loadUser("Long Term Affiliate", "affiliate-longterm@retail.com", secret,
                Arrays.asList(Role.AFFILIATE, Role.CUSTOMER), DateTimeUtil.nowMinusYearsInUTC(5)));
    }

    private User loadUser(String name, String email, String password, List<Role> roles, Instant createdAt) {
        User user = new User();
        user.setCreatedAt(createdAt);
        user.setEmail(email);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);
        return user;
    }

}
