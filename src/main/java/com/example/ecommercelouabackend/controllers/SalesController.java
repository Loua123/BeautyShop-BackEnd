package com.example.ecommercelouabackend.controllers;

import com.example.ecommercelouabackend.dto.AddSalesDTO;
import com.example.ecommercelouabackend.dto.AddproductRequest;
import com.example.ecommercelouabackend.entities.Product;
import com.example.ecommercelouabackend.entities.Sales;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.services.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SalesController {
    private final SalesService salesService;
    @PostMapping("/addSales")
    public Sales addSales(@RequestBody AddSalesDTO request) throws NotFoundEntityException {
        Sales sales = new Sales();
        sales.setIdBuyer(request.getIdBuyer());
        sales.setIdOrder(request.getIdOrder());
        sales.setIdSeller(request.getIdSeller());
        salesService.add(sales);
        return sales;
    }
    @GetMapping("/GetallSales")
    public List<Sales> GetallSales () throws NotFoundEntityException {
        return salesService.getAll();
    }
    }
