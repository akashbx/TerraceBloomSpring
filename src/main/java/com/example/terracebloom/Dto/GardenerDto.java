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
public class GardenerDto {
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

    public static GardenerDto from (Gardener gardener) {
        GardenerDto gardenerDto = new GardenerDto();
        gardenerDto.setId(gardener.getId());
        gardenerDto.setName(gardener.getName());
        gardenerDto.setDescription(gardener.getDescription());
        gardenerDto.setPrice(gardener.getPrice());
        gardenerDto.setAddress(gardener.getAddress());
        gardenerDto.setCity(gardener.getCity());
        gardenerDto.setState(gardener.getState());
        gardenerDto.setZipcode(gardener.getZipcode());
        gardenerDto.setPhone(gardener.getPhone());
        gardenerDto.setEmail(gardener.getEmail());
        gardenerDto.setImage(gardener.getImage());
        gardenerDto.setPassword(gardener.getPassword());
        return gardenerDto;
    }
}
