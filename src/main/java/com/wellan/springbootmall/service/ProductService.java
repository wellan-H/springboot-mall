package com.wellan.springbootmall.service;

import com.wellan.springbootmall.dto.ProductQueryParams;
import com.wellan.springbootmall.dto.ProductRequest;
import com.wellan.springbootmall.model.Product;

import java.util.List;

public interface ProductService {
    //回傳商品列表
    List<Product> getProducts(ProductQueryParams productQueryParams);
//    回傳根據查詢條件下的商品總數
    Integer countProduct(ProductQueryParams productQueryParams);

    //提供功能，根據商品id得到商品
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);


    void deleteProductById(Integer productId);

}
