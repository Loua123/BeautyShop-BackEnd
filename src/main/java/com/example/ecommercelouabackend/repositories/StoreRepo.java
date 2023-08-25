package com.example.ecommercelouabackend.repositories;

import com.example.ecommercelouabackend.entities.Store;
import com.example.ecommercelouabackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepo extends JpaRepository<Store,Long> {
    List<Store> findByUserId(Long userId);
}
