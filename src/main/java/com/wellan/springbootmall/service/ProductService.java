package com.wellan.springbootmall.service;

import com.wellan.springbootmall.model.Product;

public interface ProductService {
    //提供功能，根據商品id得到商品
    Product getProductById(Integer productId);

}
