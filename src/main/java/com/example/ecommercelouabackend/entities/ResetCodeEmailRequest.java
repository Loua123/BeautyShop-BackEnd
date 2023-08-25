package com.example.ecommercelouabackend.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetCodeEmailRequest {
    private String resetCode;
    private String email;
}
