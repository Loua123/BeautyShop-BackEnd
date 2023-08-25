package com.example.ecommercelouabackend.services;

import com.example.ecommercelouabackend.entities.Sales;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.repositories.SalesRepo;
import com.example.ecommercelouabackend.services.interfaces.ICurd;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SalesService implements ICurd<Sales> {
private final SalesRepo salesRepo;

    public SalesService(SalesRepo salesRepo) {
        this.salesRepo = salesRepo;
    }

    @Override
    public Sales add(Sales sales) throws NotFoundEntityException {
        if (sales !=null)
            return this.salesRepo.save(sales);
        throw new NotFoundEntityException("no sales found , sales is null");
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void update(Sales sales) {

    }

    @Override
    public Sales get(long id) throws NotFoundEntityException {
        return this.salesRepo.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("could not found sales with id " + id));
    }

    @Override
    public List<Sales> getAll() {
        return this.salesRepo.findAll();
    }
}
