package com.example.store.service;

import com.example.store.dto.CustomerDTO;
import com.example.store.entity.Customer;
import com.example.store.interfaces.CustomerService;
import com.example.store.mapper.CustomerMapper;
import com.example.store.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> retrieveCustomersByName(String name, int pageNumber, int pageSize) {
        Page<Customer> customerPage = customerRepository.findByNameContainingIgnoreCase(name, PageRequest.of(pageNumber, pageSize));
        return customerMapper.customersToCustomerDTOs(customerPage.getContent());

    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO));
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> retrieveAllCustomers(int pageNumber, int pageSize) {
        Page<Customer> customerPage = customerRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return customerMapper.customersToCustomerDTOs(customerPage.getContent());

    }
}
