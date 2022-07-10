package com.wellan.springbootmall.controller;

import com.wellan.springbootmall.dto.CreateOrderRequest;
import com.wellan.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
//    創建訂單
    @PostMapping("/users/{userId}/orders")//訂單必定附屬於帳號之下
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest){
        Integer orderId = orderService.createOrder(userId,createOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }
}
