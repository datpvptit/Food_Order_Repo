package com.datpham.foodorder.entities;

import com.datpham.foodorder.Keys.IdMenuRestaurant;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Date;

@Entity(name="menu_restaurant")
public class MenuRestaurant {
    @EmbeddedId
    IdMenuRestaurant idMenuRestaurant;

    @ManyToOne
    @JoinColumn(name = "cate_id", insertable = false, updatable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "res_id", insertable = false, updatable = false)
    private Restaurant restaurant;

    private Date creatDate;

    public IdMenuRestaurant getIdMenuRestaurant() {
        return idMenuRestaurant;
    }

    public void setIdMenuRestaurant(IdMenuRestaurant idMenuRestaurant) {
        this.idMenuRestaurant = idMenuRestaurant;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }
}
