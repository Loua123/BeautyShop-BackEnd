package com.example.ecommercelouabackend.repositories;

import com.example.ecommercelouabackend.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepo extends JpaRepository<Sales,Long> {
}
