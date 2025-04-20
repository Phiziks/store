package com.example.store.controller;

import com.example.store.dto.OrderCustomerDTO;
import com.example.store.dto.OrderDTO;
import com.example.store.dto.ProductDto;
import com.example.store.entity.Customer;
import com.example.store.entity.Order;
import com.example.store.entity.Product;
import com.example.store.mapper.CustomerMapper;
import com.example.store.repository.CustomerRepository;
import com.example.store.repository.OrderRepository;
import com.example.store.service.OrderServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@ComponentScan(basePackageClasses = CustomerMapper.class)
@RequiredArgsConstructor
class OrderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private OrderRepository orderRepository;

    @MockitoBean
    private CustomerRepository customerRepository;

    private Order order;
    private Customer customer;

    @MockitoBean
    private OrderServiceImp orderService;

    private OrderDTO orderDTO;

    private OrderCustomerDTO orderCustomerDTO;

    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setName("John Doe");
        customer.setId(1L);

        order = new Order();
        order.setId(1L);
        order.setCustomer(customer);

        orderCustomerDTO = new OrderCustomerDTO();
        orderCustomerDTO.setName("John Doe");
        orderCustomerDTO.setId(1L);

        productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setOrderIdList(List.of(1L,2L));
        productDto.setDescription("Test Product");

        orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setProducts(List.of(productDto));
        orderDTO.setCustomer(orderCustomerDTO);


    }

    @Test
    void testCreateOrder() throws Exception {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(orderRepository.save(order)).thenReturn(order);
        when(orderService.createOrder(orderDTO)).thenReturn(orderDTO);

        mockMvc.perform(post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$..id").value(1L));
    }

    @Test
    void testGetAllOrders() throws Exception {
        when(orderRepository.findAll()).thenReturn(List.of(order));
        when(orderService.getAllOrders(0, 1)).thenReturn(List.of(orderDTO));
        mockMvc.perform(get("/order").param("pageNumber", "0").param("pageSize", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customer.name").value("John Doe"));
    }

    @Test
    void testGetOrder() throws Exception {
        when(orderRepository.findById(1L)).thenReturn(Optional.ofNullable(order));
        when(orderService.retrieveOrder(1L)).thenReturn(orderDTO);
        mockMvc.perform(get("/order/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..products.[0].description").value("Test Product"))
                .andExpect(jsonPath("$..customer.name").value("John Doe"));
    }
}
