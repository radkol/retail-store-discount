package com.retailstore.discounts.service.dto;

import com.retailstore.discounts.domain.Role;
import com.retailstore.discounts.domain.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserDto {

    @NotNull
    private String name;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    @NotNull
    private Instant createdAt;

    private Set<String> roles;

    public UserDto() {
        // Empty constructor needed for Jackson.
    }

    public UserDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.roles = user.getRoles().stream()
                .map(Role::name)
                .collect(Collectors.toSet());
    }

}
