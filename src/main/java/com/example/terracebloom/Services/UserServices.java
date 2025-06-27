package com.example.terracebloom.Services;

import com.example.terracebloom.Dto.UserDto;
import com.example.terracebloom.Entity.User;
import com.example.terracebloom.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UserServices {
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public void createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        user.setCity(userDto.getCity());
        user.setState(userDto.getState());
        user.setZipcode(userDto.getZipcode());
        user.setImage(userDto.getImage());

        userRepository.save(user);
    }

    public User getUserById(Integer id){
        return userRepository.findById(id).get();
    }

    public void updateUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userDto.getId()));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        user.setCity(userDto.getCity());
        user.setState(userDto.getState());
        user.setZipcode(userDto.getZipcode());
        user.setImage(userDto.getImage());

        userRepository.save(user);
    }


    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

    public void getAllUsers(){
        userRepository.findAll();
    }
}
