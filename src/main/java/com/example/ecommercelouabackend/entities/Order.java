package com.example.ecommercelouabackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@EqualsAndHashCode
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float total;
    private int quantity;
    private float price;
    @Temporal(TemporalType.DATE)
    private Date date;
    private float total_ht;
    private float total_ttc;
    private boolean status;

    @ManyToMany(mappedBy = "orders", cascade = CascadeType.ALL) // Add cascade configuration
    private Collection<Product> productCollections;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("orders")
    private User user;

    private Long id_seller;
    @ManyToOne
    private Delivery delivery;

}
