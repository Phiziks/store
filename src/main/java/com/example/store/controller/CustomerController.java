package com.example.store.controller;

import com.example.store.dto.CustomerDTO;
import com.example.store.interfaces.ICustomerService;
import com.example.store.mapper.CustomerMapper;
import com.example.store.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final ICustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getAllCustomers(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return customerService.retrieveAllCustomers(pageNumber, pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    @GetMapping(value = "/name/{name}")
    public List<CustomerDTO> getCustomerByName(@PathVariable String name, @RequestParam int pageNumber, @RequestParam int pageSize) {
        return customerService.retrieveCustomersByName(name, pageNumber, pageSize);
    }
}
