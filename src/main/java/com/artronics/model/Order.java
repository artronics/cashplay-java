package com.artronics.model;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "order_type")
public class Order extends BaseModel {

    @OneToOne(optional = false, cascade = CascadeType.ALL, targetEntity = Invoice.class)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    private String description;

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
