package com.wellan.springbootmall.dao;

import com.wellan.springbootmall.constant.ProductCategory;
import com.wellan.springbootmall.dto.ProductQueryParams;
import com.wellan.springbootmall.dto.ProductRequest;
import com.wellan.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    //回傳商品列表
    List<Product> getProducts(ProductQueryParams productQueryParams);

    //    根據productId搜尋商品
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

    Integer countProduct(ProductQueryParams productQueryParams);

    void updateStock(Integer productId, Integer stock);
}
