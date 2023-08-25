package com.example.ecommercelouabackend.repositories;

import com.example.ecommercelouabackend.entities.Order;
import com.example.ecommercelouabackend.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepo extends JpaRepository<Promotion,Long> {
}
