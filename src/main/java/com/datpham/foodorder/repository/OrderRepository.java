package com.datpham.foodorder.repository;

import com.datpham.foodorder.entities.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order getOrderById(int id);
    List<Order> findAllByStatusIsFalse();
}
