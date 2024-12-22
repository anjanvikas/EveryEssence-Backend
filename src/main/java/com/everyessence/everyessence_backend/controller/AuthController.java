package com.everyessence.everyessence_backend.controller;

import com.everyessence.everyessence_backend.dto.Response.ApiResponse;
import com.everyessence.everyessence_backend.dto.auth.AuthRequest;
import com.everyessence.everyessence_backend.model.User;

import com.everyessence.everyessence_backend.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // register

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody AuthRequest authRequest)
    {
        try {
            User newUser = new User();
            newUser.setEmail(authRequest.getEmail());
            newUser.setName(authRequest.getName());
            newUser.setPassword(authRequest.getPassword());

            String resultMessage = authService.RegisterUser(newUser);

            ApiResponse<String> response = new ApiResponse<>(true, resultMessage, null);

            
            return new ResponseEntity<>(response, HttpStatus.CREATED);
            
        } catch (Exception e) {
            // Handle exception: if email is already in use
            ApiResponse<String> response = new ApiResponse<>(false, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }
    
    // login

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody AuthRequest authRequest)
    {
        try {
            User newUser = new User();
            newUser.setEmail(authRequest.getEmail());
            newUser.setPassword(authRequest.getPassword());

            String resultMessage = authService.AuthenticateUser(newUser);

            ApiResponse<String> response = new ApiResponse<>(true, resultMessage, null);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
            
        } catch (Exception e) {
  
            ApiResponse<String> response = new ApiResponse<>(false, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }
}
