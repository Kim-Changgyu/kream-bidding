package com.example.kreambidding.controller;

import com.example.kreambidding.controller.dto.ProductDTO;
import com.example.kreambidding.model.product.Category;
import com.example.kreambidding.model.product.Gender;
import com.example.kreambidding.model.product.Product;
import com.example.kreambidding.service.ProductService;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/api/v1/products")
    public Product createProduct(@RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @GetMapping("/api/v1/products")
    public List<Product> getProducts(
            @RequestParam("category") @Nullable Category category,
            @RequestParam("brand") @Nullable String brand,
            @RequestParam("keyword") @Nullable String keyword,
            @RequestParam("gender") @Nullable Gender gender) {

        return productService.getProducts(category, brand, keyword, gender);
    }

    @GetMapping("/api/v1/products/{id}")
    public Product getProduct(@PathVariable("id") long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/api/v1/products")
    public Product getProduct(@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }

    @DeleteMapping("/api/v1/products/{id}")
    public void deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
    }
}
