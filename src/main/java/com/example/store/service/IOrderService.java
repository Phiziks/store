package com.example.store.service;

import com.example.store.dto.OrderDTO;

import java.util.List;

public interface IOrderService {
    List<OrderDTO> getAllOrders(int pageNumber, int pageSize);

    OrderDTO retrieveOrder(Long id);

    OrderDTO createOrder(OrderDTO orderDTO);
}
