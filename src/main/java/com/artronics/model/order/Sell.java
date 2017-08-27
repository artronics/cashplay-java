package com.artronics.model.order;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("sell")
public class Sell extends Order {
}
