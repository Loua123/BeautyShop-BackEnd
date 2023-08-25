package com.example.ecommercelouabackend.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RefuseStoreRequest {
    private long iduser;
    private long idstore;
    private String motif;
}
