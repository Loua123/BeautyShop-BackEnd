package com.example.ecommercelouabackend.services;

import com.example.ecommercelouabackend.entities.Category;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.repositories.CategoryRepo;
import com.example.ecommercelouabackend.services.interfaces.ICurd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService implements ICurd<Category> {

    private final CategoryRepo categoryRepo;
    @Override
    public Category add(Category category) throws NotFoundEntityException {
        if(category != null)
            return this.categoryRepo.save(category);
        throw new NotFoundEntityException("no Category found , category is null");
    }

    @Override
    public void delete(long id) {
        this.categoryRepo.findById(id)
                .ifPresent(this.categoryRepo::delete);
    }

    @Override
    public void update(Category category) {
        if(category != null)
            this.categoryRepo.save(category);
    }

    @Override
    public Category get(long id) throws NotFoundEntityException {
        return this.categoryRepo.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("could not found category with id " + id));
    }

    @Override
    public List<Category> getAll() {
        return this.categoryRepo.findAll();
    }
}
