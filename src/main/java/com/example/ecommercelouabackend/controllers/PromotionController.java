package com.example.ecommercelouabackend.controllers;

import com.example.ecommercelouabackend.dto.addPromotionDTO;
import com.example.ecommercelouabackend.entities.Promotion;
import com.example.ecommercelouabackend.exceptions.NotFoundEntityException;
import com.example.ecommercelouabackend.services.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PromotionController {
private  final PromotionService promotionService ;
    @PostMapping("AddPromotion")
    public void addPromotion(@RequestBody Promotion promotion) throws NotFoundEntityException {
        promotionService.add(promotion);
    }
}
