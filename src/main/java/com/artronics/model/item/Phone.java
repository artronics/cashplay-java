package com.artronics.model.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("phone")
public class Phone extends Item {
}
