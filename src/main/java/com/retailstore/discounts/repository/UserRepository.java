package com.retailstore.discounts.repository;

import com.retailstore.discounts.domain.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {

    Collection<User> findAll();

    Optional<User> findByKey(String key);

    void save(User user);

}
