package com.example.ecommercelouabackend.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@EqualsAndHashCode
public class Delivery {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String total;
    private String adress;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "delivery")
    @JsonIgnore
    private Collection<Order> orders;

}
