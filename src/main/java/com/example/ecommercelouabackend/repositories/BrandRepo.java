package com.example.ecommercelouabackend.repositories;

import com.example.ecommercelouabackend.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepo extends JpaRepository<Brand,Long> {
}
