package com.immutlyproducts.services;

import com.immutlyproducts.dtos.PriceUpdateMultipleDto;
import com.immutlyproducts.dtos.ProductDto;
import com.immutlyproducts.entities.Product;
import com.immutlyproducts.exceptions.InvalidPriceException;
import com.immutlyproducts.exceptions.InvalidRequestException;
import com.immutlyproducts.exceptions.ProductNotFoundException;
import com.immutlyproducts.repositories.ProductRepository;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    Integer lastProductIndex = 0;
    Integer secondLastProductIndex = 16;

    @Test
    void getAllProduct() {
        List<Product> allProduct = productService.getAllProduct();
        assertEquals(allProduct.size(), allProduct.size());
        for(Product product : allProduct) {
            System.out.println(product.toString());
        }
    }

    @Test
    void getProductById() throws ProductNotFoundException {
        try {
            Product productById = productService.getProductById(2);
            assertEquals(productById.getName(), "Asus Vivobook 15");
            System.out.println(productById.toString());
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createProduct() throws InvalidRequestException {
        ProductDto createProduct = new ProductDto();
        createProduct.setName("Name");
        createProduct.setDescription("Description");
        createProduct.setPrice(0.1);
        createProduct.setAvailibility("Availibility");

        productService.createProduct(createProduct);

        List<Product> allProduct = productService.getAllProduct();
        assertEquals(allProduct.size(), allProduct.size());

        for(Product product : allProduct){
            System.out.println(product.toString());
        }
    }

    @Test
    void updateProduct() throws ProductNotFoundException, InvalidRequestException {

        List<Product> allProduct = productService.getAllProduct();
        for(Product product : allProduct){
            lastProductIndex = product.getId();
        }
        Product productById = productService.getProductById(lastProductIndex);
        productById.setName("Name Update");
        productById.setDescription("Description Update");
        productById.setPrice(0.2);
        productById.setAvailibility("Availability Update");

        ProductDto productDto = new ProductDto();
        productDto.setName(productById.getName());
        productDto.setDescription(productById.getDescription());
        productDto.setPrice(productById.getPrice());
        productDto.setAvailibility(productById.getAvailibility());

        productService.updateProduct(lastProductIndex, productDto);



        Product updatedProduct = productService.getProductById(lastProductIndex);
        assertEquals(updatedProduct.getName(), "Name Update");
        assertEquals(updatedProduct.getDescription(), "Description Update");
        assertEquals(updatedProduct.getPrice(), 0.2);
        assertEquals(updatedProduct.getAvailibility(), "Availability Update");
        System.out.println(updatedProduct.toString());
    }

    @Test
    void updatePriceMultipleProducts() throws InvalidPriceException, ProductNotFoundException {
        List<Product> allProduct = productService.getAllProduct();
        for(Product product : allProduct){
            lastProductIndex = product.getId();
        }
        System.out.println("Last Index: " + lastProductIndex + " , Second Last Index: " + secondLastProductIndex);
        List<PriceUpdateMultipleDto> listUpdate = new ArrayList<>();

        PriceUpdateMultipleDto junior_one = new PriceUpdateMultipleDto();
        junior_one.setId(lastProductIndex);
        junior_one.setPrice(28250.00);

        PriceUpdateMultipleDto junior_two = new PriceUpdateMultipleDto();
        junior_two.setId(secondLastProductIndex);
        junior_two.setPrice(0.5);

        listUpdate.add(junior_one);
        listUpdate.add(junior_two);

        try {
            productService.updatePriceMultipleProducts(listUpdate);
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvalidPriceException e) {
            throw new RuntimeException(e);
        }

        Product junior_one_updatedPrice = productService.getProductById(secondLastProductIndex);
        Product junior_two_updatedPrice = productService.getProductById(lastProductIndex);

        List<Product> updatedProducts = new ArrayList<>();
        updatedProducts.add(junior_one_updatedPrice);
        updatedProducts.add(junior_two_updatedPrice);

        assertEquals(junior_two_updatedPrice.getPrice(), 0.5);
        assertEquals(junior_one_updatedPrice.getPrice(), 0.5);

        for(Product product : updatedProducts){
            System.out.println(product.toString());
        }
    }

    @Test
    void deleteProduct() throws ProductNotFoundException {
        List<Product> allProduct = productService.getAllProduct();
        for(Product product : allProduct){
            lastProductIndex = product.getId();
        }
        try {
            productService.deleteProduct(lastProductIndex);
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<Product> allProductUpdated = productService.getAllProduct();
        assertEquals(allProductUpdated.size(), allProductUpdated.size());
        for(Product product : allProductUpdated){
            System.out.println(product.toString());
        }
    }
}