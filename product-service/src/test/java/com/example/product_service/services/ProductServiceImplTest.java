package com.example.product_service.services;

import com.example.product_service.domain.Product;
import com.example.product_service.domain.dto.ProductDto;
import com.example.product_service.mapper.Mapper;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Autowired
    private Mapper<Product, ProductDto> productProductDtoMapper;

    @Autowired @InjectMocks
    private ProductServiceImpl productService;

    List<Product> products = new ArrayList<>();

    // Using this so I can somewhat dynamically get the product by id
    String id="68415f566a0dce1a7850534d";

    @BeforeEach
    void beforeFunction(){
        products.add(Product.builder().id("68415f566a0dce1a7850534d").productName("Mac").description("pc portable elegant").price(new BigDecimal("1204")).build());
        products.add(Product.builder().id("68415f566a0dce1a78505789").productName("Asus ROG STRIX").description("pc portable gamer").price(new BigDecimal("2405")).build());
        products.add(Product.builder().id("68415f566a0dce1a78505kl7").productName("TV samsung 70'").description("tv 70 pouces parfaites").price(new BigDecimal("9785")).build());
    }

    @Test
    void save() {
        Product product = Product.builder().productName("Mac").description("pc portable elegant").price(new BigDecimal("1204")).build();

        when(productRepository.save(product)).thenAnswer(invocationOnMock -> {
            product.setId("68415f566a0dce1a7850534d");
            return product;
        });

        Product pro = productService.save(productProductDtoMapper.mapTo(product));
        assertAll("",
                ()->assertNotNull(pro,"Product mustn't be null"),
                ()->assertEquals("68415f566a0dce1a7850534d",pro.getId())
        );

    }

    @Test
    void getAllProducts() {
        when(productRepository.findAll()).thenReturn(products);

        List<Product> products1 = productService.getAllProducts();

        assertAll("",
                ()->assertNotNull(products1,"Mustn't be null"),
                ()->assertEquals(3,products1.size(),"Must have 3 items inside"),
                ()->assertEquals("Mac",products1.get(0).getProductName(),"Checking the item name")
        );

    }

    @Test
    void findProductById() {

        when(productRepository.findById(id)).thenReturn( products.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst());

        Product product = productService.findProductById(id);

        assertAll("",
                ()->assertNotNull(product,"The object must not be null"),
                ()->assertEquals(id,product.getId(),"The product must have the same id I am looking for")
        );

    }

    @Test
    void deleteProduct() {
    }
}