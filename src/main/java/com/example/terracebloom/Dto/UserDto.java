package com.example.terracebloom.Dto;

import com.example.terracebloom.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String image;
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public static  UserDto from (User user) {
       UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        userDto.setPhone(user.getPhone());
        userDto.setAddress(user.getAddress());
        userDto.setCity(user.getCity());
        userDto.setState(user.getState());
        userDto.setZipcode(user.getZipcode());
        userDto.setImage(user.getImage());
        return userDto;
    }

}
