package com.example.terracebloom.Services;

import com.example.terracebloom.Dto.GardenerDetails;
import com.example.terracebloom.Dto.GardenerDto;
import com.example.terracebloom.Dto.GardenerRatingsResponse;
import com.example.terracebloom.Dto.RatingDto;
import com.example.terracebloom.Entity.Gardener;
import com.example.terracebloom.Repository.GardenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GardenerServices {

    @Autowired
    private GardenerRepository gardenerRepository;

    public void createGardener(GardenerDto gardenerDto){
        Gardener gardener = new Gardener();

        gardener.setName(gardenerDto.getName());
        gardener.setDescription(gardenerDto.getDescription());
        gardener.setPrice(gardenerDto.getPrice());
        gardener.setEmail(gardenerDto.getEmail());
        gardener.setPassword(gardenerDto.getPassword());
        gardener.setPhone(gardenerDto.getPhone());
        gardener.setAddress(gardenerDto.getAddress());
        gardener.setCity(gardenerDto.getCity());
        gardener.setState(gardenerDto.getState());
        gardener.setZipcode(gardenerDto.getZipcode());
        gardener.setImage(gardenerDto.getImage());
        gardenerRepository.save(gardener);
    }
    public GardenerDto getGardenerById(Integer id){
        Gardener gardener = gardenerRepository.findById(id).get();
        return GardenerDto.from(gardener);
    }
   public GardenerDetails getGardenerDetails(Integer id){
        return GardenerDetails.from(gardenerRepository.findById(id).get());
   }

    public List<GardenerDetails> getAllGardeners(){
        List<Gardener> gardeners = gardenerRepository.findAll();
        return gardeners.stream()
                .map(GardenerDetails::from)
                .collect(Collectors.toList());
    }
    public void updateGardener(GardenerDto gardenerDto) {
        Gardener existingGardener = gardenerRepository.findById(gardenerDto.getId())
                .orElseThrow(() -> new RuntimeException("Gardener not found with id: " + gardenerDto.getId()));

        existingGardener.setName(gardenerDto.getName());
        existingGardener.setEmail(gardenerDto.getEmail());
        existingGardener.setPassword(gardenerDto.getPassword());
        existingGardener.setPhone(gardenerDto.getPhone());
        existingGardener.setAddress(gardenerDto.getAddress());
        existingGardener.setCity(gardenerDto.getCity());
        existingGardener.setState(gardenerDto.getState());
        existingGardener.setZipcode(gardenerDto.getZipcode());
        existingGardener.setImage(gardenerDto.getImage());
        existingGardener.setDescription(gardenerDto.getDescription());
        existingGardener.setPrice(gardenerDto.getPrice());

        gardenerRepository.save(existingGardener);
    }
    public GardenerRatingsResponse getGardenerRatings(Integer gardenerId) {
        Gardener gardener = gardenerRepository.findById(gardenerId)
                .orElseThrow(() -> new RuntimeException("Gardener not found"));

        List<RatingDto> ratingDtos = gardener.getRatings()
                .stream()
                .map(RatingDto::from)
                .toList();

        float avgRating = 0f;
        if (!gardener.getRatings().isEmpty()) {
            float total = 0f;
            for (var r : gardener.getRatings()) {
                total += r.getRating();
            }
            avgRating = total / gardener.getRatings().size();
        }

        return new GardenerRatingsResponse(
                gardener.getId(),
                gardener.getName(),
                gardener.getDescription(),
                gardener.getPrice(),
                gardener.getEmail(),
                gardener.getPhone(),
                gardener.getImage(),
                avgRating,
                ratingDtos
        );
    }
}
