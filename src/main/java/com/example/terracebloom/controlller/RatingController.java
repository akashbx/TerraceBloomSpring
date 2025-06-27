package com.example.terracebloom.controlller;

import com.example.terracebloom.Dto.RatingDto;
import com.example.terracebloom.Request.RatingRequest;
import com.example.terracebloom.Services.RatingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/rating")
public class RatingController {

    @Autowired
    private RatingServices ratingServices;

    @PostMapping
    public void createRating(RatingRequest ratingRequest) {
        ratingServices.createRating(ratingRequest);
    }

    @GetMapping("/{id}")
    public RatingDto getRatingById(Integer id) {
        return ratingServices.getRatingById(id);
    }

    @PutMapping("/update")
    public void updateRating(RatingRequest ratingRequest) {
        ratingServices.updateRating(ratingRequest);
    }

}
