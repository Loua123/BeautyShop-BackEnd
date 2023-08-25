package com.example.ecommercelouabackend.services;

import com.example.ecommercelouabackend.entities.SubCategory;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.repositories.SubCategoryRepo;
import com.example.ecommercelouabackend.services.interfaces.ICurd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCategoryService implements ICurd<SubCategory> {

    private final SubCategoryRepo categoryRepo;
    @Override
    public SubCategory add(SubCategory subCategory) throws NotFoundEntityException {
        if(subCategory != null)
            return this.categoryRepo.save(subCategory);
        throw new NotFoundEntityException("no SubCategory found , subCategory is null");
    }

    @Override
    public void delete(long id) {
        this.categoryRepo.findById(id)
                .ifPresent(this.categoryRepo::delete);
    }

    @Override
    public void update(SubCategory subCategory) {
        if(subCategory != null)
            this.categoryRepo.save(subCategory);
    }

    @Override
    public SubCategory get(long id) throws NotFoundEntityException {
        return this.categoryRepo.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("could not found subCategory with id " + id));
    }

    @Override
    public List<SubCategory> getAll() {
        return this.categoryRepo.findAll();
    }
}
