package com.example.terracebloom.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50)
    private String name;

    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Column(length = 10)
    private String phone;

    private String address;

    private String city;

    private String state;

    private String zipcode;

    private String image;
}
