package com.example.ecommercelouabackend.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String lastname;
    private String firstname;
    private String password;
    private String username;
    private String adress;
    private String city ;
    private int telephone;
    private int cin;
    private String image;

}