package com.example.product_service.mapper.impl;


import com.example.product_service.domain.Product;
import com.example.product_service.domain.dto.ProductDto;
import com.example.product_service.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ProductDto> {

    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private ModelMapper modelMapper;

    @Override
    public ProductDto mapTo(Product product) {
        return modelMapper.map(product,ProductDto.class);
    }

    @Override
    public Product mapFrom(ProductDto productDto) {
        return modelMapper.map(productDto,Product.class);
    }
}
