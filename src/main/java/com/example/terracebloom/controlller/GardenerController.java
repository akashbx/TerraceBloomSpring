package com.example.terracebloom.controlller;

import com.example.terracebloom.Dto.GardenerDetails;
import com.example.terracebloom.Dto.GardenerDto;
import com.example.terracebloom.Dto.GardenerRatingsResponse;
import com.example.terracebloom.Services.GardenerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/gardener")
public class GardenerController {
    @Autowired
    private GardenerServices gardenerServices;

    @PostMapping
    private void createGardener(@RequestBody GardenerDto gardenerDto){
        gardenerServices.createGardener(gardenerDto);
    }
    @PutMapping("/update")
    private void updateGardener(@RequestBody GardenerDto gardenerDto){
        gardenerServices.updateGardener(gardenerDto);
    }

    @GetMapping("/{id}")
    private ResponseEntity<GardenerDto> getGardener(@PathVariable Integer id){
        GardenerDto gardenerDto = gardenerServices.getGardenerById(id);
        return ResponseEntity.ok(gardenerDto);
    }
    @GetMapping("/profile/{id}")
    public ResponseEntity<GardenerRatingsResponse> getGardenerRatings(@PathVariable Integer id) {
        return ResponseEntity.ok(gardenerServices.getGardenerRatings(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<GardenerDetails>> getAllGardeners(){
        List<GardenerDetails> gardenerDetails = gardenerServices.getAllGardeners();
        return ResponseEntity.ok(gardenerDetails);
    }

}
