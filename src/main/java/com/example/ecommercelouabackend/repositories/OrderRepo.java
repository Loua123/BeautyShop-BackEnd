package com.example.ecommercelouabackend.repositories;

import com.example.ecommercelouabackend.entities.Order;
import com.example.ecommercelouabackend.entities.Product;
import com.example.ecommercelouabackend.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Long> {
    List<Order> findByUserId(Long userId);

}
