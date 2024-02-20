package com.datpham.foodorder.entities;

import com.datpham.foodorder.Keys.IdOrderDetail;
import jakarta.persistence.*;

import java.sql.Date;

@Entity(name = "order_item")
public class OrderItem {
    @EmbeddedId
    private IdOrderDetail idOrderDetail;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "food_id", insertable = false, updatable = false)
    private Food food;

    @Column(name = "create_date")
    private Date createDate;
    public IdOrderDetail getIdOrderDetail() {
        return idOrderDetail;
    }

    public void setIdOrderDetail(IdOrderDetail idOrderDetail) {
        this.idOrderDetail = idOrderDetail;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

