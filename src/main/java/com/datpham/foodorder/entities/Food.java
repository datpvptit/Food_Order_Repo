package com.datpham.foodorder.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="image")
    private String image;

    @Column(name="time_ship")
    private String timeShip;

    @Column(name="price")
    private double price;

    @ManyToOne
    @JoinColumn(name="cate_id")
    private Category category;

    @OneToMany(mappedBy = "food")
    private List<RatingFood> ratingFoodList;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTimeShip() {
        return timeShip;
    }

    public void setTimeShip(String timeShip) {
        this.timeShip = timeShip;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<RatingFood> getRatingFoodList() {
        return ratingFoodList;
    }

    public void setRatingFoodList(List<RatingFood> ratingFoodList) {
        this.ratingFoodList = ratingFoodList;
    }
}
