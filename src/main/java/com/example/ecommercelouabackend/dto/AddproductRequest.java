package com.example.ecommercelouabackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddproductRequest {

    private String name;
    private String price;
    private String description;
    private String image;
    private String categories;
    private String soucat;
    private Long idstore;
}
