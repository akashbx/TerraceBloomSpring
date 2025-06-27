package com.example.terracebloom.Entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Gardener")
public class Gardener {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String name;

    @Column(length = 1000)
   private String description;

   private Float price;

   private String address;

   private String city;

   private String state;

   private String zipcode;

   private String phone;

   private String email;

   private String image;

   private String password;

   @OneToMany(mappedBy = "gardenerId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;
}