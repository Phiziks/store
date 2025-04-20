package com.example.store.interfaces;

import com.example.store.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders(int pageNumber, int pageSize);

    OrderDTO retrieveOrder(Long id);

    OrderDTO createOrder(OrderDTO orderDTO);
}
