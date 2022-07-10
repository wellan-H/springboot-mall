package com.wellan.springbootmall.service;

import com.wellan.springbootmall.dto.CreateOrderRequest;
import com.wellan.springbootmall.model.Order;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);
}
