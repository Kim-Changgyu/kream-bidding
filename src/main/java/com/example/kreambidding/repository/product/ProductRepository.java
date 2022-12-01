package com.example.kreambidding.repository.product;

import com.example.kreambidding.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM product WHERE category LIKE IFNULL(:category, '%') AND brand LIKE IFNULL(:brand, '%') AND name LIKE IF(:name IS NULL, '%', CONCAT('%', :name, '%')) AND gender LIKE IFNULL(:gender, '%')", nativeQuery = true)
    List<Product> findAllByFilters(String category, String brand, String name, String gender);
}
