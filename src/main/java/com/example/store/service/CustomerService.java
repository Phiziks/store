package com.example.store.service;

import com.example.store.dto.CustomerDTO;
import com.example.store.entity.Customer;
import com.example.store.interfaces.ICustomerService;
import com.example.store.mapper.CustomerMapper;
import com.example.store.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> retrieveCustomersByName(String name, int pageNumber, int pageSize) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        Page<Customer> customerPage = customerRepository.findByNameContainingIgnoreCase(name,PageRequest.of(pageNumber, pageSize));
        for (Customer customer : customerPage) {
            CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;

    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO));
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> retrieveAllCustomers(int pageNumber, int pageSize) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        Page<Customer> customerPage = customerRepository.findAll(PageRequest.of(pageNumber, pageSize));
        for (Customer customer : customerPage) {
            CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
            customerDTOList.add(customerDTO);
        }

        return customerDTOList;
    }
}
