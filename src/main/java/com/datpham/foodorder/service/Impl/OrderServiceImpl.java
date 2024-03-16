package com.datpham.foodorder.service.Impl;

import com.datpham.foodorder.Keys.IdOrderDetail;
import com.datpham.foodorder.dto.FoodDTO;
import com.datpham.foodorder.dto.OrderDTO;
import com.datpham.foodorder.dto.OrderItemDTO;
import com.datpham.foodorder.entities.*;
import com.datpham.foodorder.payload.Reponse.OrderItemDetailRespone;
import com.datpham.foodorder.payload.Reponse.OrderResponse;
import com.datpham.foodorder.payload.Reponse.OrderResponseDetail;
import com.datpham.foodorder.payload.ResponseData;
import com.datpham.foodorder.repository.*;
import com.datpham.foodorder.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
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

            responseData.setSuccess(true);
            responseData.setDesc("" + order1.getId());
            return responseData;

        }catch (Exception e){
            System.out.println("Fail to add order");
        }
        return null;
    }

    @Override
    public List<OrderResponseDetail> getAllToServe() {

        List<Order> orderList = orderRepository.findAllByStatusIsFalse();
        if(orderList.isEmpty()){
            return null;
        }
        List<OrderResponseDetail> orderResponseDetailList = new ArrayList<>();
        for(Order order : orderList){
            OrderResponseDetail orderResponseDetail = new OrderResponseDetail();
            if(order.getPayment() != null){
                orderResponseDetail.setCusName(order.getPayment().getCustomerName());
                orderResponseDetail.setCustomer_phone_number(order.getPayment().getCustomerPhoneNumber());
            }else {
                orderResponseDetail.setCusName("Unknown");
                orderResponseDetail.setCustomer_phone_number("Unknown");
            }
            orderResponseDetail.setId(order.getId());
            orderResponseDetail.setTotalPrice(order.getTotalPrice());
            orderResponseDetail.setTime(order.getCreateDate());
            orderResponseDetail.setStatus(order.isStatus());
            orderResponseDetail.setIs_pay(order.getPayment() != null);

            List<OrderItemDetailRespone> itemDetailResponeList = new ArrayList<>();
            for(OrderItem orderItem : order.getListOrderItem()){
                OrderItemDetailRespone orderItemDetailRespone = new OrderItemDetailRespone();
                orderItemDetailRespone.setFoodName(orderItem.getFood().getTitle());
                orderItemDetailRespone.setQuanity(orderItem.getNumber());
                orderItemDetailRespone.setImage(orderItem.getFood().getImage());
                itemDetailResponeList.add(orderItemDetailRespone);
            }
            orderResponseDetail.setDetailResponeList(itemDetailResponeList);
            orderResponseDetailList.add(orderResponseDetail);
        }
        return orderResponseDetailList;

    }

    @Override
    @Transactional
    public Boolean serveOrder(Integer id) {
        try{
            Order order = orderRepository.getOrderById(id);
            order.setStatus(true);
            orderRepository.save(order);
            return true;
        }catch (RuntimeException e){
            throw  new RuntimeException("Can't serve this order");
        }
    }

    @Override
    public OrderResponseDetail getDetail(Integer id) {
        OrderResponseDetail orderResponseDetail = new OrderResponseDetail();
        Order order = orderRepository.getOrderById(id);
        if(order == null){
            return null;
        }
        List<OrderItemDetailRespone> itemDetailResponeList = new ArrayList<>();
        orderResponseDetail.setUserName(order.getUser().getFulname());
        orderResponseDetail.setTotalPrice(order.getTotalPrice());
        orderResponseDetail.setTime(order.getCreateDate());
        orderResponseDetail.setStatus(order.isStatus());
        orderResponseDetail.setIs_pay(order.getPayment() != null);

        for(OrderItem orderItem : order.getListOrderItem()){
            OrderItemDetailRespone orderItemDetailRespone = new OrderItemDetailRespone();
            orderItemDetailRespone.setFoodName(orderItem.getFood().getTitle());
            orderItemDetailRespone.setQuanity(orderItem.getNumber());
            orderItemDetailRespone.setImage(orderItem.getFood().getImage());
            itemDetailResponeList.add(orderItemDetailRespone);
        }
        orderResponseDetail.setDetailResponeList(itemDetailResponeList);

        return orderResponseDetail;
    }

    @Override
    public List<OrderResponse> getAll() {
        List<Order> orderList = orderRepository.findAll();
        if(orderList.isEmpty()){
            return null;
        }
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for(Order order : orderList){
            OrderResponse orderResponse = new OrderResponse();
            if(order.getPayment() != null){
                orderResponse.setCusName(order.getPayment().getCustomerName());
                orderResponse.setCustomer_phone_number(order.getPayment().getCustomerPhoneNumber());
            }else {
                orderResponse.setCusName("Unknown");
                orderResponse.setCustomer_phone_number("Unknown");
            }
            orderResponse.setId(order.getId());
            orderResponse.setUserName(order.getUser().getFulname());
            orderResponse.setTotalPrice(order.getTotalPrice());
            orderResponse.setTime(order.getCreateDate());

            orderResponse.setStatus(order.isStatus());
            orderResponse.setIs_pay(order.getPayment() != null);
            orderResponseList.add(orderResponse);
        }
        return orderResponseList;
    }
}
