package com.datpham.foodorder.payload.Reponse;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class OrderResponse {
    private String userName;
    private String cusName;
    private LocalDateTime time;
    private String customer_phone_number;
    private Double totalPrice;
    private Boolean status;
    private Boolean is_pay;
}
