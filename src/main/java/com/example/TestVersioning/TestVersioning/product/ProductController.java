package com.example.TestVersioning.TestVersioning.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping(value = "/product", produces = {"application/json", "application/xml"})
    public Product getProduct() {
        return new Product(1, "Laptop", 750.00);
    }

}


