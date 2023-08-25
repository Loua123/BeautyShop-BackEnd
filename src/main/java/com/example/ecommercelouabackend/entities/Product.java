package com.example.ecommercelouabackend.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@EqualsAndHashCode
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String price;
    private String description;
    private String image;
    private String categories;
    private String soucat;

    @ManyToOne
    private SubCategory subcategory ;

    @ManyToMany
    @JsonIgnore
    @ToString.Exclude
    private List<Order> orders;

    @ManyToOne
    @JsonIgnore
    private User provider ;

    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private Store store;

}
