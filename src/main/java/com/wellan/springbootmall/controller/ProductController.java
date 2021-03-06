package com.wellan.springbootmall.controller;

import com.wellan.springbootmall.constant.ProductCategory;
import com.wellan.springbootmall.dto.ProductQueryParams;
import com.wellan.springbootmall.dto.ProductRequest;
import com.wellan.springbootmall.model.Product;
import com.wellan.springbootmall.service.ProductService;
import com.wellan.springbootmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("/products")//查詢商品列表
    public ResponseEntity<Page<Product>> getProducts(
//            查詢條件 filtering
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
//            排序sorting
            @RequestParam(defaultValue = "created_date")  String orderBy,//表示根據甚麼欄位排序，如價格、日期等
            @RequestParam(defaultValue = "desc")  String sort,//表示為升序asc或是降序desc排序
//            分頁pagination
//            用以保護後端效能
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,//表示取得幾筆資料，最大值不大於1000最小值不得為負
            @RequestParam(defaultValue = "0") @Min(0) Integer offset//表示跳過幾筆資料

    ){
        //將所有變數放入新的ProductQueryParams，統一管理參數
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);
//        取得productlist
        List<Product> productList = productService.getProducts(productQueryParams);
//        取得product總數
        Integer total = productService.countProduct(productQueryParams);//傳入查詢條件
        //預期根據查詢條件，回傳條件下的商品總數有幾筆
//        分頁
        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);//商品總數資訊
        page.setResults(productList);
        return ResponseEntity.status(HttpStatus.OK).body(page);
//        固定回傳200OK狀態碼，不因沒有商品而回傳NOT_FOUND
//        RestFul的設計理念，每一個url都是一個資源，即使當中的資料不存在，
//        但GET/products的資源是存在的
    }
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
