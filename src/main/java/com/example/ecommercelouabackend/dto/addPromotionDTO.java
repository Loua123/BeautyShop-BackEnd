package com.example.ecommercelouabackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class addPromotionDTO {
    private long idProduct;
    private float newPrice;
}
