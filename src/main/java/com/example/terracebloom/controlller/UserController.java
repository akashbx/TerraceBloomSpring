package com.example.terracebloom.controlller;

import com.example.terracebloom.Dto.UserDto;
import com.example.terracebloom.Entity.User;
import com.example.terracebloom.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/user")
@RestController
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping
    public void createUser(@RequestBody UserDto userDto){
        userServices.createUser(userDto);
    }
    @PutMapping("/update")
    public void updateUser(@RequestBody UserDto userDto){
        userServices.updateUser(userDto);
    }
    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody Integer id){
        userServices.deleteUser(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userServices.getUserById(id));
    }
}
