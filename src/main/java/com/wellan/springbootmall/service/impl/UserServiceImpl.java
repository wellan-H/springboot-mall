package com.wellan.springbootmall.service.impl;

import com.wellan.springbootmall.dao.UserDao;
import com.wellan.springbootmall.dto.UserRegisterRequest;
import com.wellan.springbootmall.model.User;
import com.wellan.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);

    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
