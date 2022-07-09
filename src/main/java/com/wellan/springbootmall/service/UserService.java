package com.wellan.springbootmall.service;

import com.wellan.springbootmall.dto.UserLoginRequest;
import com.wellan.springbootmall.dto.UserRegisterRequest;
import com.wellan.springbootmall.model.User;

public interface UserService {
    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User login(UserLoginRequest userLoginRequest);
}
