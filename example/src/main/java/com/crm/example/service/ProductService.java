package com.crm.example.service;

import com.crm.example.model.Product;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {


    public List<Product> getListProductsExample() {
        return Arrays.asList(
                new Product("Program", "Програмка для продажи", 100, "product_java.png"),
                new Product("Service consulting", "сервис - консультация", 1000, "product_java.png"),
                new Product("license", "Лицензия", 10000, "product_java.png")
        );
    }
}
