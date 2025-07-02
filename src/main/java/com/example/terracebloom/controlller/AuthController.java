package com.example.terracebloom.controlller;

import com.example.terracebloom.Dto.ResponseMessage;
import com.example.terracebloom.Dto.UserDto;
import com.example.terracebloom.Entity.JWTRequest;
import com.example.terracebloom.Entity.JWTResponse;
import com.example.terracebloom.Entity.UserPrincipal;
import com.example.terracebloom.Security.JwtHelper;
import com.example.terracebloom.Services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserServices userServices;
    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody JWTRequest request) {
        this.doAuthenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);
        Integer userId = null;
        if (userDetails instanceof UserPrincipal) {
            userId = ((UserPrincipal) userDetails).getUser().getId(); // <-- Add this getter in UserPrincipal if missing
        }

        JWTResponse response = JWTResponse.builder()
                .jwtToken(token)
                .email(userDetails.getUsername())
                .userid(userId)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
    @PostMapping("/signup")
    public ResponseEntity<ResponseMessage> registerUser(@RequestBody UserDto userDto) {
        try {
            userServices.createUser(userDto); // handles password encoding internally
            return ResponseEntity.ok(new ResponseMessage("success", "User registered successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseMessage("error", "Registration failed: " + e.getMessage()));
        }
    }

}
