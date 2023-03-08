package com.eshopTest.eshopTest.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;
@Entity
@Table(name ="orders")
public class Order {
    @Id
    @GeneratedValue
    private Long orderId;
    private long quantity;
    private  long code;
    private float discount;
    @OneToMany(mappedBy ="order")
    private Set<Product> products;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId.equals(order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", quantity=" + quantity +
                ", code=" + code +
                ", discount=" + discount +
                ", products=" + products +
                '}';
    }
}
