package com.example.store.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {

    private Long id;

    private String description;

    private List<Long> orderIdList;


}
