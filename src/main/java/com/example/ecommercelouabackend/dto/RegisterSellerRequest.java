package com.example.ecommercelouabackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class RegisterSellerRequest {
    private String lastname;
    private String firstname;
    private String password;
    private String username;
    private String adress;
    private String city;
    private long telephone;
    private long cin;
    private String image;
    private Date ownerDOB;
    private String nameStore;
    private String adresseStore;
    private String villeStore;
    private String codepostalStore;
    private String type_de_pieceStore;
    private String img_de_pieceStore;
    private boolean matriculefiscaleStore;
    private String valueOfmatriculefiscaleStore;
}
