package com.crm.example.controller;

import com.crm.example.model.Product;
import com.crm.example.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/hello")
    public String getHelloPage(Model model) {
        model.addAttribute("title", "CRM IT System");
        model.addAttribute("description", "<strong>Matvei is boss</strong>");
        return "hello.html";
    }

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getListProductsExample();
        model.addAttribute("products", products);
        return "products.html";
    }
}
