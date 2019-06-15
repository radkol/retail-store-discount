package com.retailstore.discounts.web.rest.vm;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginVM {

    @NotNull
    @Email
    @Size(min = 5, max = 254)
    private String username;

    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVM{" + "username='" + username + '\'' + '}';
    }

}
