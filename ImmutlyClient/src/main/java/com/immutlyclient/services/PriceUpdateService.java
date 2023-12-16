package com.immutlyclient.services;

import com.immutlyclient.constants.PriceUpdateConstant;
import com.immutlyclient.dtos.PriceUpdateDto;
import com.immutlyclient.dtos.Product;
import com.immutlyclient.dtos.ProductDto;
import com.immutlyclient.exceptions.PriceInvalidException;
import com.immutlyclient.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceUpdateService {
    @Autowired
    public WebClient webClientPriceUpdate;

    // PUT - Update prices of multiple products
    public List<Product> updatePrices(List<PriceUpdateDto> priceUpdateDtoList) throws ProductNotFoundException, PriceInvalidException {
        List<Product> priceUpdatedproducts = new ArrayList<>();
        for(PriceUpdateDto request : priceUpdateDtoList){
            Integer id = request.getId();
            Double price = request.getPrice();
            if(id == null){
                throw new ProductNotFoundException("Product Not Found With Given ID");
            }
            if(price < 0){
                throw new PriceInvalidException("Price invalid for ID: " + id + ", Other IDs with valid price will be updated.");
            }
            ProductDto product = webClientPriceUpdate
                    .get()
                    .uri(PriceUpdateConstant.getProductById, id)
                    .retrieve()
                    .bodyToMono(ProductDto.class)
                    .block();

            product.setPrice(price);

            webClientPriceUpdate
                    .put()
                    .uri(PriceUpdateConstant.updateProductById, id)
                    .syncBody(product)
                    .retrieve()
                    .bodyToMono(ProductDto.class)
                    .block();

            Product updated_product = webClientPriceUpdate
                    .get()
                    .uri(PriceUpdateConstant.getProductById, id)
                    .retrieve()
                    .bodyToMono(Product.class)
                    .block();

            priceUpdatedproducts.add(updated_product);
        }
        return priceUpdatedproducts;
    }
}
