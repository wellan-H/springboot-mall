package com.wellan.springbootmall.dto;

import javax.validation.constraints.NotBlank;

public class UserRegisterRequest {
    @NotBlank//除了不可為null也不可為空白
    private String email;
    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
