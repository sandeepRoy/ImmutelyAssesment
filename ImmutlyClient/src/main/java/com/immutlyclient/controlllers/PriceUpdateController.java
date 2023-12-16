package com.immutlyclient.controlllers;

import com.immutlyclient.dtos.PriceUpdateDto;
import com.immutlyclient.dtos.Product;
import com.immutlyclient.exceptions.PriceInvalidException;
import com.immutlyclient.exceptions.ProductNotFoundException;
import com.immutlyclient.services.PriceUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/immutly")
public class PriceUpdateController {
    @Autowired
    private PriceUpdateService priceUpdateService;

    @PutMapping("/priceUpdate")
    public ResponseEntity<List<Product>> updatePrices(@RequestBody List<PriceUpdateDto> priceUpdateDtoList) throws PriceInvalidException, ProductNotFoundException {
        List<Product> products = priceUpdateService.updatePrices(priceUpdateDtoList);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }
}
