package com.wellan.springbootmall.service.impl;

import com.wellan.springbootmall.dao.OrderDao;
import com.wellan.springbootmall.dao.ProductDao;
import com.wellan.springbootmall.dto.BuyItem;
import com.wellan.springbootmall.dto.CreateOrderRequest;
import com.wellan.springbootmall.model.Order;
import com.wellan.springbootmall.model.OrderItem;
import com.wellan.springbootmall.model.Product;
import com.wellan.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;//取得商品資訊


    @Transactional//確保all or never
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
//        計算訂單的總花費
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();
        for(BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());
//            計算總價錢
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount+amount;
//            轉換BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setPorductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }
        //創建訂單
        Integer orderId = orderDao.createOrder(userId,totalAmount);
        orderDao.createOrderItems(orderId,orderItemList);//在OrderItem中，插入相關資料
        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);
        List<OrderItem> orderItemList = orderDao.getOrderIntemsByOrderId(orderId);
//        一筆Order會包含多個orderItem，也就是一個orderItemList
        order.setOrderItemList(orderItemList);
        return order;
    }
}
