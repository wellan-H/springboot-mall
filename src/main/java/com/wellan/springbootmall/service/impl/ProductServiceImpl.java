package com.wellan.springbootmall.service.impl;

import com.wellan.springbootmall.constant.ProductCategory;
import com.wellan.springbootmall.dao.ProductDao;
import com.wellan.springbootmall.dto.ProductQueryParams;
import com.wellan.springbootmall.dto.ProductRequest;
import com.wellan.springbootmall.model.Product;
import com.wellan.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductDao productDao;

    @Override
    public Integer countProduct(ProductQueryParams productQueryParams) {
        return productDao.countProduct(productQueryParams);
    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        return productDao.getProducts(productQueryParams);

    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return  productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
         productDao.updateProduct(productId,productRequest);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
