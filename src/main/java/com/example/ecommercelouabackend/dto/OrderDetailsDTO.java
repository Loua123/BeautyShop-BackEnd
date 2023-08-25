package com.example.ecommercelouabackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class OrderDetailsDTO {
    private List<ProductOrderDTO> orderDetails;
    private Long userid;
}
