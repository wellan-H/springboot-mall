package com.wellan.springbootmall.controller;

import com.wellan.springbootmall.dto.ProductRequest;
import com.wellan.springbootmall.model.Product;
import com.wellan.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {
    @GetMapping("/products")//查詢商品列表
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> productList = productService.getProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
//        固定回傳200OK狀態碼，不因沒有商品而回傳NOT_FOUND
//        RestFul的設計理念，每一個url都是一個資源，即使當中的資料不存在，
//        但GET/products的資源是存在的
    }
    @Autowired
    private ProductService productService;
    @GetMapping("/products/{productId}")//查詢特定商品
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
      Product product = productService.getProductById(productId);
      if(product!=null){
          return ResponseEntity.status(HttpStatus.OK).body(product);
      }else {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
    }
    //新增商品功能
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Integer productId = productService.createProduct(productRequest);
        Product product = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
//    修改商品功能
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId ,
                                                 @RequestBody @Valid ProductRequest productRequest){
        //先根據productId檢查是否存在此商品資料
        Product product = productService.getProductById(productId);
        if(product==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //修改商品資料
        productService.updateProduct(productId,productRequest);
        Product updateProduct = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        //對前端來說，執行此操作有兩種情況:
//        1.商品存在，成功刪除
//        2.商品本就不存在
//        因此不需要確認是否有此商品
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
