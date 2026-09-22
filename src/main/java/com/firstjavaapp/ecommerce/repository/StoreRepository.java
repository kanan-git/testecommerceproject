package com.firstjavaapp.ecommerce.repository;

import com.firstjavaapp.ecommerce.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    boolean existsByName(String name);
}
