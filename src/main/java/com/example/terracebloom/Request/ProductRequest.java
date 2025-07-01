package com.example.terracebloom.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private String category;
    private String image;
    private String image2;
    private String image3;

}
