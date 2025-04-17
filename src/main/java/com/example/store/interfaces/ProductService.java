package com.example.store.interfaces;

import com.example.store.dto.ProductDto;

import java.util.List;

public interface ProductService {
    String creatProduct(ProductDto productDto);

    ProductDto findProductById(Long id);

    List<ProductDto> retrieveAllProducts(int pageNumber, int pageSize);
}
