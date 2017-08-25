package com.artronics.controller;

import com.artronics.model.Account;
import com.artronics.model.Customer;
import com.artronics.repository.AccountRepository;
import com.artronics.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RepositoryRestController()
@RequestMapping("/api")
public class CustomerController extends ApiController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping(path = "/account/{accountId}/customers")
    public ResponseEntity<Customer> create(Authentication auth,
                                           @RequestBody() Customer customer,
                                           @PathVariable("accountId") Long accountId,
                                           @RequestParam(name = "userId") Long userId) {
        checkAuth(auth, accountId, userId);

        Account account = new Account();
        account.setId(accountId);
        customer.setAccount(account);
        customer = customerRepository.save(customer);

        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }

    @PutMapping(path = "/account/{accountId}/customers/{customerId}")
    public ResponseEntity<Customer> update(Authentication auth,
                                           @RequestBody() Customer customer,
                                           @PathVariable("accountId") Long accountId,
                                           @PathVariable("customerId") Long customerId,
                                           @RequestParam(name = "userId") Long userId) {

        checkAuth(auth, accountId, userId);

        Customer old = customerRepository.findOne(customerId);
        if (old == null) {
            throw new ResourceNotFoundException();
        }
        Account account = new Account();
        account.setId(accountId);

        customer.setId(customerId);
        customer.setAccount(account);
        customer = customerRepository.save(customer);

        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @GetMapping(path = "/customers/search")
    public ResponseEntity<PagedResources<Customer>> search(@RequestParam("q") String q,
                                                           @RequestParam("page") int page,
                                                           @RequestParam("size") int size) {
        Pageable pageable = new PageRequest(page, size);
        Page<Customer> customers = customerRepository.search(pageable, q);

        PagedResources.PageMetadata metadata = new PagedResources.PageMetadata(
                customers.getSize(),
                customers.getNumber(),
                customers.getTotalElements(),
                customers.getTotalPages());

        // TODO fix links
        PagedResources<Customer> pagedResources = new PagedResources<>(customers.getContent(), metadata, new Link("kir"));

        return new ResponseEntity<>(pagedResources, HttpStatus.OK);
    }
}
