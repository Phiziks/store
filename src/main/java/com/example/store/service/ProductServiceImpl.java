package com.example.store.service;

import com.example.store.dto.ProductDto;
import com.example.store.entity.Product;
import com.example.store.interfaces.ProductService;
import com.example.store.mapper.ProductMapper;
import com.example.store.repository.OrderProductRepository;
import com.example.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final OrderProductRepository orderProductRepository;


    @Override
    public String creatProduct(ProductDto productDto) {
        Product product = new Product();
        product.setDescription(productDto.getDescription());

        productRepository.save(product);

        return "Product has been created";
    }

    @Override
    public ProductDto findProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            ProductDto product = productMapper.productToProductDto(productOptional.get());
            List<Long> orderIdList = orderProductRepository.findOrderIdsByProduct(productOptional.get().getId());
            product.setOrderIdList(orderIdList);
            return product;
        } else {
            log.error("Product with id {} not found", id);
            throw new RuntimeException("Product with id " + id + " not found");
        }

    }

    @Override
    public List<ProductDto> retrieveAllProducts(int pageNumber, int pageSize) {

        Page<Product> productPage = productRepository.findAll(PageRequest.of(pageNumber, pageSize));
        List<ProductDto> productDtoList = productMapper.productsListToProductsDtoList(productPage.getContent());
        for (ProductDto productDto : productDtoList) {
            List<Long> orderIdList = orderProductRepository.findOrderIdsByProduct(productDto.getId());
            productDto.setOrderIdList(orderIdList);
        }
        return productDtoList;
    }
}
