package com.example.terracebloom.controlller;

import com.example.terracebloom.Dto.RatingDto;
import com.example.terracebloom.Dto.ResponseMessage;
import com.example.terracebloom.Entity.Rating;
import com.example.terracebloom.Request.RatingRequest;
import com.example.terracebloom.Request.RatingUpdateRequest;
import com.example.terracebloom.Services.RatingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/rating")
public class RatingController {

    @Autowired
    private RatingServices ratingServices;

    @PostMapping()
    public ResponseEntity<ResponseMessage> submitRating(
            @RequestPart("data") RatingRequest data,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {
       ratingServices.saveRating(data, imageFile);
        return ResponseEntity.ok(new ResponseMessage("Success","Rating submitted successfully"));
    }

    @GetMapping("/{id}")
    public RatingDto getRatingById(Integer id) {
        return ratingServices.getRatingById(id);
    }

    @PutMapping("/updateRating/{id}")
    public ResponseEntity<Rating> updateRating(
            @PathVariable Integer id,
            @RequestPart("data") RatingUpdateRequest request,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {

        Rating updated = ratingServices.updateRating(id, request, imageFile);
        return ResponseEntity.ok(updated);
    }



}
