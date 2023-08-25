package com.example.ecommercelouabackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
public class addStoreRequest {
    private Date ownerDOB;
    private String nameStore;
    private String adresseStore;
    private String villeStore;
    private String codepostalStore;
    private String type_de_pieceStore;
    private String img_de_pieceStore;
    private boolean matriculefiscaleStore;
    private String valueOfmatriculefiscaleStore;
    private Long iduser;
}
