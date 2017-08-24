package com.artronics.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Account extends BaseModel {

    @OneToMany(mappedBy = "account", targetEntity = User.class, fetch = FetchType.EAGER)
    private Collection<User> users;

    @OneToMany(mappedBy = "account", targetEntity = Customer.class, fetch = FetchType.LAZY)
    private Collection<Customer> customers;

    @Column(nullable = false, unique = true)
    private String name;

    public Account() {
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Collection<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Collection<Customer> customers) {
        this.customers = customers;
    }

    public Account(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
