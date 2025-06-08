package com.example.order_service.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private String orderNumber;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
