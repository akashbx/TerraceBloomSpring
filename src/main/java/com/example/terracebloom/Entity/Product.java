package com.example.terracebloom.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(length = 100)
    private String description;

    private Float price;

    @Enumerated(EnumType.STRING)
    @NonNull
    private Category category;

    private String image;

    private String image2;

    private String image3;
}
