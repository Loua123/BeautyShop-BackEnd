package com.example.ecommercelouabackend.services;


import com.example.ecommercelouabackend.entities.Delivery;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.repositories.DeliveryRepo;
import com.example.ecommercelouabackend.services.interfaces.ICurd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService  implements ICurd<Delivery> {


    private final DeliveryRepo deliveryRepo;
    @Override
    public Delivery add(Delivery delivery) throws NotFoundEntityException {
        if(delivery != null)
            return this.deliveryRepo.save(delivery);
        throw new NotFoundEntityException("no Delivery found , delivery is null");
    }

    @Override
    public void delete(long id) {
        this.deliveryRepo.findById(id)
                .ifPresent(this.deliveryRepo::delete);
    }

    @Override
    public void update(Delivery delivery) {
        if(delivery != null)
            this.deliveryRepo.save(delivery);
    }

    @Override
    public Delivery get(long id) throws NotFoundEntityException {
        return this.deliveryRepo.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("could not found delivery with id " + id));
    }

    @Override
    public List<Delivery> getAll() {
        return this.deliveryRepo.findAll();
    }
}
