package com.example.ecommercelouabackend.services;

import com.example.ecommercelouabackend.entities.Brand;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.repositories.BrandRepo;
import com.example.ecommercelouabackend.services.interfaces.ICurd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BrandService implements ICurd<Brand> {

    private final BrandRepo brandRepo;
    @Override
    public Brand add(Brand brand) throws NotFoundEntityException {
        if(brand != null)
            return this.brandRepo.save(brand);
        throw new NotFoundEntityException("no Brand found , brand is null");
    }

    @Override
    public void delete(long id) {
        this.brandRepo.findById(id)
                .ifPresent(this.brandRepo::delete);
    }

    @Override
    public void update(Brand brand) {
        if(brand != null)
            this.brandRepo.save(brand);
    }

    @Override
    public Brand get(long id) throws NotFoundEntityException {
        return this.brandRepo.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("could not found brand with id " + id));

    }

    @Override
    public List<Brand> getAll() {
        return this.brandRepo.findAll();

    }
}
