package com.example.ecommercelouabackend.repositories;

import com.example.ecommercelouabackend.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepo extends JpaRepository<Delivery,Long> {
}
