package com.example.tangboyang1.pojo;

import java.util.Date;

public class ShoppingCart {
    private Integer id;

    private Integer userId;

    private Integer storeId;

    private Integer productsId;

    private Integer num;

    private Date creattime;

    private Double muney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getProductsId() {
        return productsId;
    }

    public void setProductsId(Integer productsId) {
        this.productsId = productsId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public Double getMuney() {
        return muney;
    }

    public void setMuney(Double muney) {
        this.muney = muney;
    }
}