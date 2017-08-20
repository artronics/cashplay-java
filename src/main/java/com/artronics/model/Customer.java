package com.artronics.model;

import javax.persistence.Entity;

@Entity
public class Customer extends BaseModel {
    private String firstName;
    private String lastName;

    public Customer(String firstName) {
        this.firstName = firstName;
    }

    public Customer() {
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
