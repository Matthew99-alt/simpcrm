package com.crm.example.controller;


import com.crm.example.model.Product;
import com.crm.example.service.ProductService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class MainRestController {

    private final ProductService productService;

    public MainRestController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/hello")
    public String sayHello() {
        return "Hello world!";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getListProductsExample();
    }
}
