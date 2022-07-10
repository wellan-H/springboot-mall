package com.wellan.springbootmall.dao;

import com.wellan.springbootmall.model.Order;
import com.wellan.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderIntemsByOrderId(Integer orderId);
}
