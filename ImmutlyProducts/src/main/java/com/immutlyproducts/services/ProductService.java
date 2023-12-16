package com.immutlyproducts.services;

import com.immutlyproducts.dtos.PriceUpdateMultipleDto;
import com.immutlyproducts.dtos.ProductDto;
import com.immutlyproducts.entities.Product;
import com.immutlyproducts.exceptions.InvalidPriceException;
import com.immutlyproducts.exceptions.InvalidRequestException;
import com.immutlyproducts.exceptions.ProductNotFoundException;
import com.immutlyproducts.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // GET - List<Products>

    public List<Product> getAllProduct(){
        List<Product> list_products = productRepository.findAll();
        return list_products;
    }

    // GET - Product by id
    public Product getProductById(Integer id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with given id"));
        return product;
    }

    // POST - Product
    public Product createProduct(ProductDto productDto) throws InvalidRequestException {
        List<Product> all_products = productRepository.findAll();
        for(Product product : all_products){
            if(product.getName().equals(productDto.getName())){
                throw new InvalidRequestException("Product Already Exists");
            }
        }
        if(productDto.getPrice() < 0) {
            throw new InvalidRequestException("Product price is lesser than Zero");
        }
        if(productDto.getAvailibility() == null || productDto.getName() == null || productDto.getDescription() == null) {
            throw new InvalidRequestException("Field / Fields can't be empty");
        }
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setAvailibility(productDto.getAvailibility());

        productRepository.save(product);
        return product;
    }

    // PUT - Product by id
    public Product updateProduct(Integer id, ProductDto productDto) throws InvalidRequestException, ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with given id"));
        if(productDto.getPrice() < 0) {
            throw new InvalidRequestException("Product price is lesser than Zero");
        }
        if(productDto.getAvailibility() == null || productDto.getName() == null || productDto.getDescription() == null) {
            throw new InvalidRequestException("Field / Fields can't be empty");
        }
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setAvailibility(productDto.getAvailibility());

        productRepository.save(product);
        return product;
    }

    // DELETE - Product by id
    public String deleteProduct(Integer id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with given id"));
        productRepository.delete(product);
        return "Deleted!";
    }

    // PUT - Update prices of multiple products
    public List<Product> updatePriceMultipleProducts(List<PriceUpdateMultipleDto> priceUpdateMultipleDto) throws ProductNotFoundException, InvalidPriceException {
        List<Product> priceUpdatedProducts = new ArrayList<>();
        for(PriceUpdateMultipleDto request : priceUpdateMultipleDto){

            Integer id = request.getId();
            Double price = request.getPrice();
            if(id == null){
                throw new ProductNotFoundException("Product not found with given id");
            }
            if(price < 0){
                throw new InvalidPriceException("Product price is lesser than Zero");
            }
            Product product = productRepository.findById(id).get();
            product.setPrice(price);

            productRepository.save(product);
            priceUpdatedProducts.add(product);
        }
        return priceUpdatedProducts;
    }

    // GET - List<Product> as per filter
    public List<Product> filterProduct(String name, Double maxPrice, Double minPrice){
        List<Product> filteredProduct = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for(Product product : products){
            if(maxPrice.equals(null) || minPrice.equals(null)){
                maxPrice = 0.0;
                minPrice = 0.0;
            }
            if(product.getName().equals(name) || product.getPrice() >= minPrice || product.getPrice() <= maxPrice){
                filteredProduct.add(product);
            }
        }
        return filteredProduct;
    }
}
