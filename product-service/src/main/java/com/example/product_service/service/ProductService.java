package com.example.product_service.service;

import com.example.product_service.domain.Product;

import java.util.List;

public interface ProductService {

    // update and add
    Product save(Product product);

    List<Product> getAllProducts();

    Product findProductById(String id);

    void deleteProduct(String id);

}
