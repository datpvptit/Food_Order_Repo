package com.datpham.foodorder.service.Impl;

import com.datpham.foodorder.Keys.IdOrderDetail;
import com.datpham.foodorder.dto.FoodDTO;
import com.datpham.foodorder.dto.OrderDTO;
import com.datpham.foodorder.dto.OrderItemDTO;
import com.datpham.foodorder.entities.Category;
import com.datpham.foodorder.entities.Food;
import com.datpham.foodorder.entities.Order;
import com.datpham.foodorder.entities.OrderItem;
import com.datpham.foodorder.payload.ResponseData;
import com.datpham.foodorder.repository.FoodRepository;
import com.datpham.foodorder.repository.OrderItemRepository;
import com.datpham.foodorder.repository.OrderRepository;
import com.datpham.foodorder.repository.UserRepository;
import com.datpham.foodorder.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;
    @Override
    @Transactional
    public ResponseData addOrder(OrderDTO orderDTO) {
        ResponseData responseData = new ResponseData();
        try {
            int maxTimeServe = 0;
            int totalPrice = 0;
            Order order = new Order();
            Order order1 = orderRepository.save(order);

            for(OrderItemDTO orderItemDTO : orderDTO.getOrderItemDTOList()){
                OrderItem orderItem = new OrderItem();
                orderItem.setFood(foodRepository.findFoodById(orderItemDTO.getFoodID()));
                maxTimeServe = Integer.max(maxTimeServe, orderItem.getFood().getTimeServe());
                totalPrice += orderItem.getFood().getTimeServe() * orderItemDTO.getNumber();
                orderItem.setOrder(order1);
                orderItem.setNumber(orderItemDTO.getNumber());
                orderItem.setIdOrderDetail(new IdOrderDetail(order1.getId(), orderItem.getFood().getId()));
                orderItemRepository.save(orderItem);
            }
            order1.setTotalPrice(totalPrice);
            order1.setTimeServe(maxTimeServe);
            order1.setUser(userRepository.findById(orderDTO.getUserId()));
            orderRepository.save(order1);

            responseData.setSuccess(true);

            return responseData;

        }catch (Exception e){
            System.out.println("Fail to add order");
        }
        return null;
    }

    @Override
    public List<OrderDTO> getAll() {

        List<Order> orderList = orderRepository.findAll();
        if(orderList.isEmpty()){
            return null;
        }
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order  : orderList){
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setUserId(order.getUser().getId());
            orderDTO.setTotalPrice(order.getTotalPrice());
            List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
            for(OrderItem orderItem : order.getListOrderItem()){
                OrderItemDTO orderItemDTO = new OrderItemDTO();
                orderItemDTO.setFoodID(orderItem.getFood().getId());
                orderItemDTO.setNumber(orderItem.getNumber());
                orderItemDTOList.add(orderItemDTO);
            }
            orderDTO.setOrderItemDTOList(orderItemDTOList);
            orderDTOList.add(orderDTO);
        }

        return orderDTOList;

    }
}
