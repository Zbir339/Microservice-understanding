package com.example.product_service.service;

import com.example.product_service.domain.Product;
import com.example.product_service.domain.dto.ProductDto;

import java.util.List;

public interface ProductService {

    // update and add
    Product save(ProductDto product);

    Product update(ProductDto product,String id);

    List<Product> getAllProducts();

    Product findProductById(String id);

    void deleteProduct(String id);

}
