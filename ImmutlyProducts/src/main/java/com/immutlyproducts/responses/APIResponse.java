package com.immutlyproducts.responses;

import com.immutlyproducts.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class APIResponse {
    private String response;
    private Product product;


    public APIResponse(String response){
        this.response = response;
    }

    public APIResponse(String response, Product product){
        this.response = response;
        this.product = product;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
