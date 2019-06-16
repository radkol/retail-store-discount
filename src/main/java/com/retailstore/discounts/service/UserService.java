package com.retailstore.discounts.service;

import com.retailstore.discounts.repository.UserRepository;
import com.retailstore.discounts.service.dto.UserDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto findByUsername(String username) {
        return userRepository.findByEmail(username).map(user -> new UserDto(user))
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " was not found"));
    }
}
