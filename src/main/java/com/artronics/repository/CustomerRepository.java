package com.artronics.repository;

import com.artronics.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    @Override
    @Query("select c from Customer c where c.account.id = ?#{principal}")
    Page<Customer> findAll(Pageable pageable);

    @Override
    @RestResource(exported = false)
    <S extends Customer> S save(S s);

    @Override
    @RestResource(exported = false)
    <S extends Customer> Iterable<S> save(Iterable<S> iterable);
}
