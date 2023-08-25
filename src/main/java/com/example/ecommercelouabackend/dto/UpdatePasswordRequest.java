package com.example.ecommercelouabackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdatePasswordRequest {
    String email;
    String oldpassword;
    String newpassword;
}
