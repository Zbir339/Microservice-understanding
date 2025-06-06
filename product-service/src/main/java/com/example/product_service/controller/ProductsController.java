package com.example.product_service.controller;


import com.example.product_service.domain.Product;
import com.example.product_service.domain.dto.ProductDto;
import com.example.product_service.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @PutMapping("/{id}") // maybe in time I'll make the update more dynamic but for now this is good
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDto product, @RequestParam String id){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.update(product,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@RequestParam String id){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("nothing to tell");
    }


}
