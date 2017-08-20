package com.artronics.controller;

import com.artronics.model.Customer;
import com.artronics.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/customers")
public class CustomerController extends ApiController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAll(@RequestParam(required = false) String limit) {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("kir"));
        customers.add(new Customer("kos"));

        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        customer = customerService.create(customer);

        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }
}
