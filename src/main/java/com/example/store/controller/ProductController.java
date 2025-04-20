package com.example.store.controller;

import com.example.store.dto.ProductDto;
import com.example.store.interfaces.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/v1/retrieve", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductDto> retrieveProduct(@RequestParam Long productId) {

        log.info("id to retrieve product : {}", productId);

        return new ResponseEntity<>(productService.findProductById(productId), HttpStatus.OK);


    }

    @GetMapping(value = "/v1/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ProductDto>> retrieveAllProducts(@RequestParam int pageNumber, @RequestParam int pageSize) {

        return new ResponseEntity<>(productService.retrieveAllProducts(pageNumber, pageSize), HttpStatus.OK);

    }

    @PostMapping(value = "/v1/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> createProduct(@RequestBody ProductDto productDto, HttpServletRequest request) {

        log.info("received ProductDto to be created: {}", productDto);

        return new ResponseEntity<>(productService.creatProduct(productDto), HttpStatus.CREATED);


    }
}
