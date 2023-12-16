package com.immutlyproducts.dtos;

import jakarta.validation.constraints.NotEmpty;

public class PriceUpdateMultipleDto {
    private Integer id;
    private Double price;

    public PriceUpdateMultipleDto() {}

    public PriceUpdateMultipleDto(Integer id, Double price) {
        this.id = id;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
