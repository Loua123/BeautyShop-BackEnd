package com.example.ecommercelouabackend.repositories;

import com.example.ecommercelouabackend.entities.Order;
import com.example.ecommercelouabackend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE p.store.id = :storeId")
    List<Product> findProductsByStoreId(@Param("storeId") Long storeId);

}