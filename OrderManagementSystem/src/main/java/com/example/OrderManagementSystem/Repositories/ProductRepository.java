package com.example.OrderManagementSystem.Repositories;

import com.example.OrderManagementSystem.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom query methods can be added here if needed, for example:
    // List<Product> findByName(String name);
}

