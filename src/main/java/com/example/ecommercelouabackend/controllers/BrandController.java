package com.example.ecommercelouabackend.controllers;


import com.example.ecommercelouabackend.entities.Brand;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.services.interfaces.ICurd;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BrandController {

    private final ICurd<Brand> brandICurd;

    @PostMapping("/addBrand")
    public ResponseEntity<Brand> addBrand(@RequestBody Brand brand) throws NotFoundEntityException {
        return ResponseEntity.ok(this.brandICurd.add(brand));
    }

    @GetMapping("/getBrandById")
    public ResponseEntity<Brand> getBrandById(@RequestParam long idBrand) throws NotFoundEntityException {
        return ResponseEntity.ok(this.brandICurd.get(idBrand));
    }

    @GetMapping("/getAllBrands")
    public ResponseEntity<List<Brand>> getAllBrands() {
        return ResponseEntity.ok(this.brandICurd.getAll());
    }

    @PutMapping("/updateBrand")
    public ResponseEntity<Brand> updateBrand(@RequestBody Brand brand) {
        this.brandICurd.update(brand);
        return ResponseEntity.ok(brand);
    }

    @DeleteMapping("/deleteBrand")
    public ResponseEntity<String> deleteBrand(@RequestParam long idBrand) {
        this.brandICurd.delete(idBrand);
        return ResponseEntity.ok("brand with id " + idBrand + " is deleted");
    }
}
