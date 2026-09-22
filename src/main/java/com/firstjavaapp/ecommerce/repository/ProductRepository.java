package com.firstjavaapp.ecommerce.repository;

import com.firstjavaapp.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
