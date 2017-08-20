package com.artronics.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("gold")
public class Gold extends Item {
    private int carret;

    public int getCarret() {
        return carret;
    }

    public void setCarret(int carret) {
        this.carret = carret;
    }
}
