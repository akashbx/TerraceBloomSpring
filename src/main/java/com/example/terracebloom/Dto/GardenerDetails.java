package com.example.terracebloom.Dto;

import com.example.terracebloom.Entity.Gardener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GardenerDetails {
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
    private RatingDto rating;
    private Float averageRating;

    public static GardenerDetails from(Gardener gardener) {
        GardenerDetails details = new GardenerDetails();
        details.setId(gardener.getId());
        details.setName(gardener.getName());
        details.setDescription(gardener.getDescription());
        details.setPrice(gardener.getPrice());
        details.setAddress(gardener.getAddress());
        details.setCity(gardener.getCity());
        details.setState(gardener.getState());
        details.setZipcode(gardener.getZipcode());
        details.setPhone(gardener.getPhone());
        details.setEmail(gardener.getEmail());
        details.setImage(gardener.getImage());
        details.setPassword(gardener.getPassword());

        if (gardener.getRatings() != null && !gardener.getRatings().isEmpty()) {
            details.setRating(RatingDto.from(gardener.getRatings().get(0)));

            float sum = 0f;
            for (var r : gardener.getRatings()) {
                sum += r.getRating();
            }
            float avg = sum / gardener.getRatings().size();
            details.setAverageRating(avg);
        } else {
            details.setRating(null);
            details.setAverageRating(0f);
        }

        return details;
    }

}
