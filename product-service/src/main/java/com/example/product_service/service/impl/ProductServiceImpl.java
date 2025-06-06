package com.example.product_service.service.impl;

import com.example.product_service.domain.Product;
import com.example.product_service.domain.dto.ProductDto;
import com.example.product_service.mapper.Mapper;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final Mapper<Product,ProductDto> productProductDtoMapper;


    public ProductServiceImpl(ProductRepository productRepository, Mapper<Product, ProductDto> productProductDtoMapper) {
        this.productRepository = productRepository;
        this.productProductDtoMapper = productProductDtoMapper;
    }

    @Override
    public Product save(ProductDto product) {
        Product pro  = productProductDtoMapper.mapFrom(product);
        pro.setModified(LocalDateTime.now());
        return productRepository.save(pro);
    }

    @Override
    public Product update(ProductDto product, String id) {

        // ajouter une mesure de check ici mais le but de ceci n'est pas de crée une app a full fonctionalite
        // mais c'est d'explorer kubernetes

        Product pro  = productProductDtoMapper.mapFrom(product);
        pro.setId(id);
        pro.setModified(LocalDateTime.now());
        return productRepository.save(pro);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(String id) {
        return productRepository.findById(id).orElse(new Product());
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
