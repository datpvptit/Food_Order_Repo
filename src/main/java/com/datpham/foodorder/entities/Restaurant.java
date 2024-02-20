package com.datpham.foodorder.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="name")
    private String name;

    @Column(name="subtitle")
    private String subtitle;

    @Column(name="description")
    private String description;


    @Column(name="image")
    private String image;


    @Column(name="is_freeship")
    private boolean isFreeship;


    @Column(name="address")
    private String address;


    @Column(name="open_time")
    private Date openTime;

    @OneToMany(mappedBy = "restaurant")
    private List<MenuRestaurant> menuRestaurantList;
    @OneToMany(mappedBy = "restaurant")
    private List<Promo> promoList;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orderList;

    @OneToMany(mappedBy = "restaurant")
    private List<RatingRestaurant> ratingRestaurantList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFreeship() {
        return isFreeship;
    }

    public void setFreeship(boolean freeship) {
        isFreeship = freeship;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public List<MenuRestaurant> getMenuRestaurantList() {
        return menuRestaurantList;
    }

    public void setMenuRestaurantList(List<MenuRestaurant> menuRestaurantList) {
        this.menuRestaurantList = menuRestaurantList;
    }

    public List<Promo> getPromoList() {
        return promoList;
    }

    public void setPromoList(List<Promo> promoList) {
        this.promoList = promoList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<RatingRestaurant> getRatingRestaurantList() {
        return ratingRestaurantList;
    }

    public void setRatingRestaurantList(List<RatingRestaurant> ratingRestaurantList) {
        this.ratingRestaurantList = ratingRestaurantList;
    }
}
