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
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description ;

    @ManyToOne
    @JsonIgnore
    private Category category ;

    @OneToMany(mappedBy = "subcategory",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Collection<Product> products;
}
