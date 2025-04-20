package com.example.store.controller;

import com.example.store.dto.OrderDTO;
import com.example.store.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDTO> getAllOrders(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return orderService.getAllOrders(pageNumber, pageSize);
    }

    @GetMapping(value = "/{id}")
    public OrderDTO singleOrder(@PathVariable Long id) {
        return orderService.retrieveOrder(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createOrder(@RequestBody OrderDTO order) {
        return orderService.createOrder(order);
    }
}
