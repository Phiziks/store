package com.example.store.controller;

import com.example.store.dto.CustomerDTO;
import com.example.store.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(value = "/all/v1")
    public List<CustomerDTO> getAllCustomers(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return customerService.retrieveAllCustomers(pageNumber, pageSize);
    }

    @PostMapping(value = "/create/v1")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    @GetMapping(value = "/name/v1")
    public List<CustomerDTO> getCustomerByName(@RequestParam String name, @RequestParam int pageNumber, @RequestParam int pageSize) {
        return customerService.retrieveCustomersByName(name, pageNumber, pageSize);
    }
}
