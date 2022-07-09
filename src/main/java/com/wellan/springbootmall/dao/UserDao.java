package com.wellan.springbootmall.dao;

import com.wellan.springbootmall.dto.UserRegisterRequest;
import com.wellan.springbootmall.model.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}
