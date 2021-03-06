package com.wellan.springbootmall.controller;

import com.wellan.springbootmall.dto.UserLoginRequest;
import com.wellan.springbootmall.dto.UserRegisterRequest;
import com.wellan.springbootmall.model.User;
import com.wellan.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
//    註冊新帳號
    @PostMapping("/users/register")//資安問題選用post
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        Integer userId = userService.register(userRegisterRequest);
        User user = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @PostMapping("/users/login")//預期傳入email與password
//    可創建對應的Object接住資訊
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        User user = userService.login(userLoginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
