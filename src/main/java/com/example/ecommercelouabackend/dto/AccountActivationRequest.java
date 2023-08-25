package com.example.ecommercelouabackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountActivationRequest {
    String code;
    String email;
}
