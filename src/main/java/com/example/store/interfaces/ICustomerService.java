package com.example.store.interfaces;

import com.example.store.dto.CustomerDTO;

import java.util.List;

public interface ICustomerService {
    List<CustomerDTO> retrieveCustomersByName(String name, int pageNumber, int pageSize);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> retrieveAllCustomers(int pageNumber, int pageSize);
}
