package com.example.ecommercelouabackend.repositories;

import com.example.ecommercelouabackend.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
