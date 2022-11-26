package com.example.testapp.security;

import javax.validation.constraints.Pattern;

public class LoginRequest {

    private String name;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?!.*[А-ЯЁа-яё])(?=.*[@#$%^&+=])(?=\\S+$).{5,10}$")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
