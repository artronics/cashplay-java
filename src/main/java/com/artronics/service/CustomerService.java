package com.artronics.service;

import com.artronics.model.Customer;
import com.artronics.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements BaseService<Customer> {
    @Autowired
    private CustomerRepository repo;

    @Override
    public Customer create(Customer entity) {
        return repo.save(entity);
    }
}
