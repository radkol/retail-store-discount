package com.retailstore.discounts.repository;

import com.retailstore.discounts.domain.Role;
import com.retailstore.discounts.domain.User;
import com.retailstore.discounts.repository.UserRepository;
import com.retailstore.discounts.util.DateTimeUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.*;

@Repository
class InMemoryUserRepository implements UserRepository, InitializingBean {

    private final PasswordEncoder passwordEncoder;

    public InMemoryUserRepository(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private final Set<User> storage = new HashSet<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        loadUsers();
    }

    @Override
    public Collection<User> findAll() {
        return storage;
    }

    @Override
    public Optional<User> findByKey(String key) {
        return storage.stream().filter(user -> user.getEmail().equalsIgnoreCase(key)).findFirst();
    }

    @Override
    public void save(User user) {
        storage.add(user);
    }

    private void loadUsers() {
        final String secret = "secret";

        storage.add(loadUser("Regular Customer", "customer@retail.com", secret, Arrays.asList(Role.CUSTOMER), DateTimeUtil.nowInUTC()));
        storage.add(loadUser("Employee User", "employee@retail.com", secret, Arrays.asList(Role.EMPLOYEE, Role.CUSTOMER), DateTimeUtil.nowInUTC()));
        storage.add(loadUser("Affiliate User", "affiliate@retail.com", secret, Arrays.asList(Role.AFFILIATE, Role.CUSTOMER), DateTimeUtil.nowInUTC()));
        storage.add(loadUser("Long Term Customer", "customer-longterm@retail.com", secret, Arrays.asList(Role.CUSTOMER), DateTimeUtil.nowMinusYearsInUTC(3)));
        storage.add(loadUser("Long Term Employee", "employee-longterm@retail.com", secret, Arrays.asList(Role.EMPLOYEE, Role.CUSTOMER), DateTimeUtil.nowMinusYearsInUTC(4)));
        storage.add(loadUser("Long Term Affiliate", "affiliate-longterm@retail.com", secret, Arrays.asList(Role.AFFILIATE, Role.CUSTOMER), DateTimeUtil.nowMinusYearsInUTC(5)));
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
