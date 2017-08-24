package com.artronics.controller;

import com.artronics.model.Account;
import com.artronics.model.Customer;
import com.artronics.repository.AccountRepository;
import com.artronics.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RepositoryRestController()
@RequestMapping("/api")
public class CustomerController extends ApiController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping(path = "/account/{id}/customers")
    public ResponseEntity<Customer> create(Authentication auth,
                                           @RequestBody() Customer customer,
                                           @PathVariable("id") Long id,
                                           @RequestParam(name = "userId") Long userId) {
        if (auth.getPrincipal() != id) {
            throw new AccessDeniedException("access denied.");
        }
        if (auth.getDetails() != userId) {
            throw new AccessDeniedException("You are not the authenticated User");
        }

        Account account = new Account();
        account.setId(id);
        customer.setAccount(account);
        customer = customerRepository.save(customer);

        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }

}
