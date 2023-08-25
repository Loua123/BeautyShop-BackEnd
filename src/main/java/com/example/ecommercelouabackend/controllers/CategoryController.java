package com.example.ecommercelouabackend.controllers;


import com.example.ecommercelouabackend.entities.Category;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.services.interfaces.ICurd;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final ICurd<Category> categoryICurd;

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) throws NotFoundEntityException {
        return ResponseEntity.ok(this.categoryICurd.add(category));
    }

    @GetMapping("/getCategoryById")
    public ResponseEntity<Category> getCategoryById(@RequestParam long idCategory) throws NotFoundEntityException {
        return ResponseEntity.ok(this.categoryICurd.get(idCategory));
    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(this.categoryICurd.getAll());
    }

    @PostMapping("/updateCategory")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        this.categoryICurd.update(category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/deleteCategory")
    public ResponseEntity<String> deleteCategory(@RequestParam long idCategory) {
        this.categoryICurd.delete(idCategory);
        return ResponseEntity.ok("category with id " + idCategory + " is deleted");
    }
}
