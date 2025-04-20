package com.example.store.service;

import com.example.store.dto.OrderDTO;
import com.example.store.dto.ProductDto;
import com.example.store.entity.Order;
import com.example.store.interfaces.OrderService;
import com.example.store.mapper.OrderMapper;
import com.example.store.repository.OrderProductRepository;
import com.example.store.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final OrderProductRepository orderProductRepository;


    @Override
    public List<OrderDTO> getAllOrders(int pageNumber, int pageSize) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        Page<Order> orderList = orderRepository.findAll(PageRequest.of(pageNumber, pageSize));
        for (Order order : orderList) {
            OrderDTO orderDTO = populateOrderIdsOnProduct(order);
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;

    }

    @Override
    public OrderDTO retrieveOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(this::populateOrderIdsOnProduct).orElse(null);


    }

    private OrderDTO populateOrderIdsOnProduct(Order order) {
        OrderDTO orderDTO = orderMapper.orderToOrderDTO(order);
        List<ProductDto> productDtoList = orderDTO.getProducts();

        for (ProductDto productDto : productDtoList) {
            productDto.setOrderIdList(orderProductRepository.findOrderIdsByProduct(productDto.getId()));
        }

        return orderDTO;
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        orderRepository.save(orderMapper.orderDTOToOrderDTO(orderDTO));
        return orderDTO;
    }

}
