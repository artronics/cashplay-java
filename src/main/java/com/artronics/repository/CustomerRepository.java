package com.artronics.repository;

import com.artronics.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    @Override
//    @Query("select c from Customer where c.account.email = ?#{principal.}")
    Page<Customer> findAll(Pageable pageable);
}
