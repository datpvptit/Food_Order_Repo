package com.datpham.foodorder.service;


import com.datpham.foodorder.dto.OrderDTO;
import com.datpham.foodorder.entities.Order;
import com.datpham.foodorder.payload.Reponse.OrderResponse;
import com.datpham.foodorder.payload.Reponse.OrderResponseDetail;
import com.datpham.foodorder.payload.ResponseData;

import java.util.List;

public interface OrderService {
    ResponseData addOrder (OrderDTO orderDTO);
    List<OrderResponse> getAllToServe();
    OrderResponseDetail getDetail(Integer id);
    List<OrderResponse> getAll();
}
