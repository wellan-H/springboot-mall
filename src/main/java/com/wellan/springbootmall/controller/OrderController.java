package com.wellan.springbootmall.controller;

import com.wellan.springbootmall.dto.CreateOrderRequest;
import com.wellan.springbootmall.dto.OrderQueryParams;
import com.wellan.springbootmall.model.Order;
import com.wellan.springbootmall.service.OrderService;
import com.wellan.springbootmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
public class OrderController {
    @Autowired
    private OrderService orderService;
//    創建訂單
    @PostMapping("/users/{userId}/orders")//訂單必定附屬於帳號之下
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest){
        Integer orderId = orderService.createOrder(userId,createOrderRequest);
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<Order>> getOrders(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "10")@Max(1000)@Min(0)Integer limit,
            @RequestParam(defaultValue = "0")@Min(0)Integer offset
    ){
        OrderQueryParams orderQueryParams = new OrderQueryParams();
        orderQueryParams.setUserId(userId);
        orderQueryParams.setLimit(limit);
        orderQueryParams.setOffset(offset);
//        取得 order list
        List<Order> orderList = orderService.getOrders(orderQueryParams);
//        取得order總數
        Integer count = orderService.countOrder(orderQueryParams);
//        分頁
        Page<Order> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orderList);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
}
