package com.example.ecommercelouabackend.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserupdateRequest {
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
