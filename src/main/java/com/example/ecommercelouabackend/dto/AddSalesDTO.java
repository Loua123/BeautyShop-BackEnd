package com.example.ecommercelouabackend.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddSalesDTO {
    private long idSeller;
    private long idBuyer;
    private long idOrder;
}
