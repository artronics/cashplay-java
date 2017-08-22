package com.artronics.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Customer extends BaseModel {

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    private String firstName;
    private String lastName;

    public Customer(Account account, String firstName, String lastName) {
        this.account = account;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
