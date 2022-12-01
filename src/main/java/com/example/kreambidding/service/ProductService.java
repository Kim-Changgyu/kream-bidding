package com.example.kreambidding.service;

import com.example.kreambidding.controller.dto.ProductDTO;
import com.example.kreambidding.model.product.Category;
import com.example.kreambidding.model.product.Gender;
import com.example.kreambidding.model.product.Product;
import com.example.kreambidding.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setCategory(productDTO.getCategory());
        product.setBrand(productDTO.getBrand());
        product.setName(productDTO.getName());
        product.setSerialNumber(productDTO.getSerialNumber());
        product.setPrice(productDTO.getPrice());
        product.setColor(productDTO.getColor());
        product.setReleasedAt(productDTO.getReleasedAt());
        product.setGender(productDTO.getGender());
        product.setSize(productDTO.getSize());

        return productRepository.save(product);
    }

    public List<Product> getProducts(Category category, String brand, String keyword, Gender gender) {
        String strCategory = category == null ? null : category.toString();
        String strGender = gender == null ? null : gender.toString();
        return productRepository.findAllByFilters(strCategory, brand, keyword, strGender);
    }

    public Product getProductById(long id) {
        return productRepository.findById(id).orElseThrow();
    }
    public Product updateProduct(ProductDTO productDTO) {
        Product product = productRepository.findById(productDTO.getId()).orElseThrow();
        product.setCategory(productDTO.getCategory() != null? productDTO.getCategory(): product.getCategory());
        product.setBrand(productDTO.getBrand() != null? productDTO.getBrand(): product.getBrand());
        product.setName(productDTO.getName() != null? productDTO.getName(): product.getName());
        product.setSerialNumber(productDTO.getSerialNumber() != null? productDTO.getSerialNumber(): product.getSerialNumber());
        product.setPrice(productDTO.getPrice() != 0L? productDTO.getPrice(): product.getPrice());
        product.setColor(productDTO.getColor() != null? productDTO.getColor(): product.getColor());
        product.setReleasedAt(productDTO.getReleasedAt() != null? productDTO.getReleasedAt(): product.getReleasedAt());
        product.setGender(productDTO.getGender() != null? productDTO.getGender(): product.getGender());
        product.setSize(productDTO.getSize() != null? productDTO.getSize(): product.getSize());

        return productRepository.save(product);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
