package com.datpham.foodorder.service.Impl;

import com.datpham.foodorder.dto.PaymentDTO;
import com.datpham.foodorder.entities.Order;
import com.datpham.foodorder.entities.Payment;
import com.datpham.foodorder.repository.OrderRepository;
import com.datpham.foodorder.repository.PaymentRepository;
import com.datpham.foodorder.service.PaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    @Override
    @Transactional
    public boolean addPayment(PaymentDTO paymentDTO) {
        try {
            Payment payment = new Payment();
            payment.setMethod(paymentDTO.getMethod());
            payment.setCustomerName(paymentDTO.getCustomerName());
            payment.setCustomerEmail(paymentDTO.getCustomerEmail());
            payment.setTotalPrice(paymentDTO.getTotalPrice());
            payment.setCustomerPhoneNumber(paymentDTO.getCustomerPhoneNumber());
            payment = paymentRepository.save(payment);
            for(int orderID : paymentDTO.getOrderIDList()){
                Order order = orderRepository.getOrderById(orderID);
                order.setPayment(payment);
                orderRepository.save(order);
            }


        } catch (Exception e){
            System.out.println("Error " + e.getMessage());
        }
        return false;
    }
}
