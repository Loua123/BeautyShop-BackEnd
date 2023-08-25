package com.example.ecommercelouabackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConfirmStoreRequest {
    private long iduser;
    private long idstore;
}
