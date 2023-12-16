package com.immutlyproducts.controllers;

import com.immutlyproducts.dtos.PriceUpdateMultipleDto;
import com.immutlyproducts.dtos.ProductDto;
import com.immutlyproducts.entities.Product;
import com.immutlyproducts.exceptions.InvalidPriceException;
import com.immutlyproducts.exceptions.InvalidRequestException;
import com.immutlyproducts.exceptions.ProductNotFoundException;
import com.immutlyproducts.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/immutly")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> allProduct = productService.getAllProduct();
        return new ResponseEntity<List<Product>>(allProduct, HttpStatus.OK);
    }

    @GetMapping("/products/{product_id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer product_id) throws ProductNotFoundException {
        Product product = productService.getProductById(product_id);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProduct(@RequestParam(required = false) String name, @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) Double minPrice) {
        List<Product> products = productService.filterProduct(name, maxPrice, minPrice);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDto productDto) throws Exception {
        Product product = productService.createProduct(productDto);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @PutMapping("/products/{product_id}")
    public ResponseEntity<Product> updateProduct(@Valid @PathVariable Integer product_id, @RequestBody ProductDto productDto) throws InvalidRequestException, ProductNotFoundException {
        Product product = productService.updateProduct(product_id, productDto);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @DeleteMapping("/products/{product_id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer product_id) throws ProductNotFoundException {
        String response = productService.deleteProduct(product_id);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @PutMapping("/products/price_update")
    public ResponseEntity<List<Product>> updatePriceMultipleProducts(@RequestBody List<PriceUpdateMultipleDto> priceUpdateMultipleDto) throws InvalidPriceException, ProductNotFoundException {
        List<Product> products = productService.updatePriceMultipleProducts(priceUpdateMultipleDto);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);

    }
}
