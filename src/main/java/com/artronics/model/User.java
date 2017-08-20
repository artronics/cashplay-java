package com.artronics.model;

import javax.persistence.Entity;

@Entity
public class User extends BaseModel{
    private String firstName;

    public User() {
    }

    public User(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
