package com.example.terracebloom.Request;

import com.example.terracebloom.Entity.Gardener;
import com.example.terracebloom.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequest {
    private Integer id;
    private User userId;
    private Gardener gardenerId;
    private Float rating;
    private String comment;
    private String image;
}
