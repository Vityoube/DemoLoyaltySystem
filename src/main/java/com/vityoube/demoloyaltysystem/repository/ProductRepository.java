package com.vityoube.demoloyaltysystem.repository;

import com.vityoube.demoloyaltysystem.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
    List<Product> findAllByPrice(Double price);
}
