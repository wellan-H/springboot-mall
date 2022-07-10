package com.wellan.springbootmall.service;

import com.wellan.springbootmall.dto.CreateOrderRequest;
import com.wellan.springbootmall.dto.OrderQueryParams;
import com.wellan.springbootmall.model.Order;

import java.util.List;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Integer countOrder(OrderQueryParams orderQueryParams);
}
