package com.example.terracebloom.Services;

import com.cloudinary.utils.ObjectUtils;
import com.example.terracebloom.Config.CloudinaryConfig;
import com.example.terracebloom.Dto.RatingDto;
import com.example.terracebloom.Entity.Gardener;
import com.example.terracebloom.Entity.Rating;
import com.example.terracebloom.Entity.User;
import com.example.terracebloom.Repository.GardenerRepository;
import com.example.terracebloom.Repository.RatingRepository;
import com.example.terracebloom.Repository.UserRepository;
import com.example.terracebloom.Request.RatingRequest;
import com.example.terracebloom.Request.RatingUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@Service
public class RatingServices {

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private CloudinaryConfig cloudinary;
    @Autowired
    private GardenerRepository gardenerRepo;
    @Autowired
    private UserRepository userRepo;

    public Rating saveRating(RatingRequest request, MultipartFile imageFile) {
        Rating rating = new Rating();
        rating.setDate(new Date());
        rating.setRating(request.getRating());
        rating.setComment(request.getComment());

        // Fetch user and gardener entities (you should already have repositories)
        Gardener gardener = gardenerRepo.findById(request.getGardenerId())
                .orElseThrow(() -> new RuntimeException("Gardener not found"));
        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        rating.setGardenerId(gardener);
        rating.setUserId(user);

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                File file = File.createTempFile("rating_", imageFile.getOriginalFilename());
                imageFile.transferTo(file);

                var uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
                String imageUrl = uploadResult.get("secure_url").toString();
                rating.setImage(imageUrl);
            } catch (Exception e) {
                throw new RuntimeException("Image upload failed", e);
            }
        }

        return ratingRepository.save(rating);
    }

    public RatingDto getRatingById(Integer id) {
        Rating rating = ratingRepository.findById(id).orElse(null);
        if (rating != null) {
            return RatingDto.from(rating);
        }
        return null;
    }
    public Rating updateRating(Integer id, RatingUpdateRequest request, MultipartFile imageFile) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found"));

        // Update fields if provided
        if (request.getRating() != null) {
            rating.setRating(request.getRating());
        }

        if (request.getComment() != null) {
            rating.setComment(request.getComment());
        }

        // Replace image if new file is uploaded
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                File temp = File.createTempFile("update_", imageFile.getOriginalFilename());
                imageFile.transferTo(temp);

                var uploadResult = cloudinary.uploader().upload(temp, ObjectUtils.asMap(
                        "folder", "ratings"
                ));
                rating.setImage(uploadResult.get("secure_url").toString());

            } catch (Exception e) {
                throw new RuntimeException("Image upload failed", e);
            }
        }

        return ratingRepository.save(rating);
    }

}
