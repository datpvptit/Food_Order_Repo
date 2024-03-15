package com.datpham.foodorder.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name="payment")
@Setter
@Getter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="method")
    private String method;

    @Column(name="customer_name")
    private String customerName;

    @Column(name="customer_email")
    private String customerEmail;

    @Column(name="customer_phone_number")
    private String customerPhoneNumber;

    @Column(name="total_price")
    private Double totalPrice;

    @OneToMany(mappedBy = "payment")
    private List<Order> orderList;

}
