package com.example.ecommercelouabackend.controllers;


import com.example.ecommercelouabackend.entities.SubCategory;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.services.SubCategoryService;
import com.example.ecommercelouabackend.services.interfaces.ICurd;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubCategoryController {

    private final ICurd<SubCategory> categoryICurd;

    @PostMapping("/addSubCategory")
    public ResponseEntity<SubCategory> addSubCategory(@RequestBody SubCategory nameSubCategory) throws NotFoundEntityException {
        return ResponseEntity.ok(this.categoryICurd.add(nameSubCategory));
    }

    @GetMapping("/getSubCategoryById")
    public ResponseEntity<SubCategory> getSubCategoryById(@RequestParam long idSubCategory) throws NotFoundEntityException {
        return ResponseEntity.ok(this.categoryICurd.get(idSubCategory));
    }
    @GetMapping("/getAllSubCategories")
    public ResponseEntity<List<SubCategory>> getAllSubCategories() {
        return ResponseEntity.ok(this.categoryICurd.getAll());
    }

    @PutMapping("/updateSubCategory")
    public ResponseEntity<SubCategory> updateSubCategory(@RequestBody SubCategory nameSubCategory) {
        this.categoryICurd.update(nameSubCategory);
        return ResponseEntity.ok(nameSubCategory);
    }
    @DeleteMapping("/deleteSubCategory")
    public ResponseEntity<String> deleteSubCategory(@RequestParam long idSubCategory) {
        this.categoryICurd.delete(idSubCategory);
        return ResponseEntity.ok("SubCategory with id " + idSubCategory + " is deleted");
    }
}
