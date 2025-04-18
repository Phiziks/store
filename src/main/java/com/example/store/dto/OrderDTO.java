package com.example.store.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private OrderCustomerDTO customer;
    private List<ProductDto> products;
}
