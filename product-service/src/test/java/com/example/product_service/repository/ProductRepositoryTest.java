package com.example.product_service.repository;


import com.example.product_service.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

@DataMongoTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test @DisplayName("check product existince")
    void testFindingProducts(){

        List<Product> products = productRepository.findAll();

        assertAll("",
            ()->assertNotNull(products,"The product list must not be null"),
            ()->assertNotEquals(0,products.size(),"The list size must not be zero"),
            ()->assertEquals("Mac",products.get(0).getProductName())
        );

    }


    @Test @DisplayName("product creation testing")
    void testingProductCreation(){
        Product product = Product.builder().productName("Mac").description("pc portable elegant").price(new BigDecimal("1204")).build();
        productRepository.save(product);
    }

    @Test @DisplayName("find a product")
    void testFindingProductById(){
        Product product = productRepository.findById("68415f566a0dce1a7850532b").orElse(null);

        assertAll("",
                ()->assertNotNull(product,"The product must not be null"),
                ()->assertEquals("Mac",product.getProductName())
        );

    }

    @Test @DisplayName("updating a product")
    void testUpdatingProductById(){
        Product product = Product.builder().id("68415f566a0dce1a7850532b").productName("Mac").description("pc portable elegant").price(new BigDecimal("4200")).build();

        productRepository.save(product);

        Product product1 = productRepository.findById("68415f566a0dce1a7850532b").orElse(null);

        assertAll("",
                ()->assertNotNull(product1,"The product must not be null"),
                ()->assertEquals(new BigDecimal("4200"),product1.getPrice(),"Product price must be updated")
        );

    }

}
