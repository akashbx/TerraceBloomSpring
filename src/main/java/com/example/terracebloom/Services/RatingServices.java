package com.example.terracebloom.Services;

import com.example.terracebloom.Dto.RatingDto;
import com.example.terracebloom.Entity.Rating;
import com.example.terracebloom.Repository.RatingRepository;
import com.example.terracebloom.Request.RatingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServices {

    @Autowired
    private RatingRepository ratingRepository;

    public void createRating(RatingRequest ratingRequest) {
        Rating rating = new Rating();
        rating.setGardenerId(ratingRequest.getGardenerId());
        rating.setUserId(ratingRequest.getUserId());
        rating.setRating(ratingRequest.getRating());
        rating.setComment(ratingRequest.getComment());
        rating.setImage(ratingRequest.getImage());
        ratingRepository.save(rating);
    }
    public RatingDto getRatingById(Integer id) {
        Rating rating = ratingRepository.findById(id).orElse(null);
        if (rating != null) {
            return RatingDto.from(rating);
        }
        return null;
    }
    public void updateRating(RatingRequest ratingRequest) {
        Rating rating = ratingRepository.findById(ratingRequest.getId()).orElse(null);
        if (rating != null) {
            rating.setGardenerId(ratingRequest.getGardenerId());
            rating.setUserId(ratingRequest.getUserId());
            rating.setRating(ratingRequest.getRating());
            rating.setComment(ratingRequest.getComment());
            rating.setImage(ratingRequest.getImage());
            ratingRepository.save(rating);
        }
    }
}
