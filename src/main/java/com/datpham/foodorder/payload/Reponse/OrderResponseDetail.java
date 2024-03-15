package com.datpham.foodorder.payload.Reponse;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponseDetail extends OrderResponse{
    List <OrderItemDetailRespone> detailResponeList;
}
