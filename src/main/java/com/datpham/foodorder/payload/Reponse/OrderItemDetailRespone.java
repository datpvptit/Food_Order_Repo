package com.datpham.foodorder.payload.Reponse;

import lombok.Data;

@Data
public class OrderItemDetailRespone {
    private String foodName;
    private String image;
    private Integer quanity;

}
