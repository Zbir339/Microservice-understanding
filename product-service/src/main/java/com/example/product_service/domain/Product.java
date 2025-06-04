package com.example.product_service.domain;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "products")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Product {

    @Id
    private String id;

    @NotNull
    @Size(min = 2, max = 100)
    private String productName;

    private String description;

    @NotNull
    @DecimalMin(value = "0.0",inclusive = false,message = "The price must be > 0")
    private BigDecimal price;

    @LastModifiedDate
    private LocalDateTime modified;
}
