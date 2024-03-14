package com.datpham.foodorder.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private int id;
    private int userId;
    private double totalPrice;
    private List<OrderItemDTO> orderItemDTOList;
}
