package com.example.terracebloom.Dto;

import com.example.terracebloom.Entity.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {
    private Integer id;
    private Integer gardenerId;
    private Integer userId;
    private Float rating;
    private String comment;

    public static RatingDto from (Rating rating) {
        RatingDto ratingDto = new RatingDto();
        ratingDto.setId(rating.getId());
        ratingDto.setGardenerId(rating.getGardenerId().getId());
        ratingDto.setUserId(rating.getUserId().getId());
        ratingDto.setRating(rating.getRating());
        ratingDto.setComment(rating.getComment());
        return ratingDto;
    }
}
