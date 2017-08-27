package com.artronics.model;


import com.artronics.model.order.Order;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Currency;

@Entity
public class Invoice extends BaseModel {

    @OneToOne(optional = false, mappedBy = "invoice")
//    @JoinColumn(name = "order_id")
    private Order order;

    private Currency total;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Currency getTotal() {
        return total;
    }

    public void setTotal(Currency total) {
        this.total = total;
    }
}
