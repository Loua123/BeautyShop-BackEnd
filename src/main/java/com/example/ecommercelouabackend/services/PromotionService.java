package com.example.ecommercelouabackend.services;

import com.example.ecommercelouabackend.entities.Order;
import com.example.ecommercelouabackend.entities.Promotion;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.repositories.ProductRepo;
import com.example.ecommercelouabackend.repositories.PromotionRepo;
import com.example.ecommercelouabackend.services.interfaces.ICurd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionService implements ICurd<Promotion> {
    private final PromotionRepo promotionRepo;


    @Override
    public Promotion add(Promotion promotion) throws NotFoundEntityException {
        if (promotion!=null) {
            System.out.println(promotion.getNewPrice());
            return this.promotionRepo.save(promotion);
        }
            throw new NotFoundEntityException("no promotion found , promotion is null");

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void update(Promotion promotion) {

    }

    @Override
    public Promotion get(long id) throws NotFoundEntityException {
        return null;
    }

    @Override
    public List<Promotion> getAll() {
        return promotionRepo.findAll();
    }
}
