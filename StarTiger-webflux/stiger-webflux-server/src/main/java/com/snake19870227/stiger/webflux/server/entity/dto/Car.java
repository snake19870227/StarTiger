package com.snake19870227.stiger.webflux.server.entity.dto;

import java.math.BigDecimal;

/**
 * @author Bu HuaYang
 */
public class Car {

    private Integer id;

    private String brand;

    private String model;

    private BigDecimal price;

    public Car() {
    }

    public Car(Integer id, String brand, String model, BigDecimal price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
