package com.example.product_service.domain.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter @AllArgsConstructor @NoArgsConstructor
public class ProductDto {

    @NotNull
    @Size(min = 2, max = 100)
    private String productName;

    private String description;

    @NotNull
    @DecimalMin(value = "0.0",inclusive = false,message = "The price must be > 0")
    private BigDecimal price;

}
