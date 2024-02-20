package com.datpham.foodorder.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name_cate")
    private String nameCate;

    @Column(name="create_date")
    private Date createDate;

    @OneToMany(mappedBy = "category")
    private List<Food> listFood;

    @OneToMany(mappedBy = "category")
    private List<MenuRestaurant> listMenuRestaurant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Food> getListFood() {
        return listFood;
    }

    public void setListFood(List<Food> listFood) {
        this.listFood = listFood;
    }

    public List<MenuRestaurant> getListMenuRestaurant() {
        return listMenuRestaurant;
    }

    public void setListMenuRestaurant(List<MenuRestaurant> listMenuRestaurant) {
        this.listMenuRestaurant = listMenuRestaurant;
    }
}
