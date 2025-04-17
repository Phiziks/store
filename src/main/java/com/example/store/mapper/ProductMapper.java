package com.example.store.mapper;

import com.example.store.dto.ProductDto;
import com.example.store.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto productToProductDto(Product product);

    List<ProductDto> productsListToProductsDtoList(List<Product> productsList);

}
