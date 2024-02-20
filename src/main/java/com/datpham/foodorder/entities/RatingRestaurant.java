package com.datpham.foodorder.entities;

import jakarta.persistence.*;

@Entity(name = "RatingRestaurant")
public class RatingRestaurant {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "res_id")
    private Restaurant restaurant;

    @Column(name = "content")
    private  String content;

    @Column(name = "rate_point")
    private  int ratePoint;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUsers() {
        return user;
    }

    public void setUsers(Users users) {
        this.user = users;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRatePoint() {
        return ratePoint;
    }

    public void setRatePoint(int ratePoint) {
        this.ratePoint = ratePoint;
    }
}