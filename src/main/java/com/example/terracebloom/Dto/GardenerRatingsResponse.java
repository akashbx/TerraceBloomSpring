package com.example.terracebloom.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GardenerRatingsResponse {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private String email;
    private String phone;
    private String image;
    private Float averageRating;
    private List<RatingDto> ratings;
}
