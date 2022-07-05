package com.wellan.springbootmall.dao;

import com.wellan.springbootmall.model.Product;

public interface ProductDao {
//    根據productId搜尋商品
    Product getProductById(Integer productId);
}
