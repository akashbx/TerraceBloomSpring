package com.example.terracebloom.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GardenerRequest {
    private Integer id;
    private String name;
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
}
