package com.datpham.foodorder.service;

import com.datpham.foodorder.dto.FoodDTO;
import com.datpham.foodorder.dto.OrderDTO;
import com.datpham.foodorder.payload.ResponseData;

import java.util.List;

public interface OrderService {
    ResponseData addOrder (OrderDTO orderDTO);
    List<OrderDTO> getAll();
}
