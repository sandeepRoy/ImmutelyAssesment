package com.immutlyproducts.dtos;

import jakarta.validation.constraints.NotNull;

public class ProductDto {
    private String name;
    private String description;
    private Double price;
    private String availibility;

    public ProductDto() { }
    public ProductDto(String name, String description, Double price, String availibility) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.availibility = availibility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAvailibility() {
        return availibility;
    }

    public void setAvailibility(String availibility) {
        this.availibility = availibility;
    }
}
