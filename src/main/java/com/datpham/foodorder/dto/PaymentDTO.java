package com.datpham.foodorder.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class PaymentDTO {
    private List<Integer> orderIDList;
    private String customerName;
    private String customerEmail;
    private String customerPhoneNumber;
    private String method;
    private Double totalPrice;
}
