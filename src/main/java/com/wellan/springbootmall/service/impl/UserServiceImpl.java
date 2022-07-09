package com.wellan.springbootmall.service.impl;

import com.wellan.springbootmall.dao.UserDao;
import com.wellan.springbootmall.dto.UserRegisterRequest;
import com.wellan.springbootmall.model.User;
import com.wellan.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {
    //添加Log功能
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
//        檢查註冊的email
        if(user!=null){//代表已被註冊
            log.warn("該email{}已被註冊",userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
//        註冊帳號
        return userDao.createUser(userRegisterRequest);

    }

    @Override
    public User getUserById(Integer userId) {

        return userDao.getUserById(userId);
    }
}
