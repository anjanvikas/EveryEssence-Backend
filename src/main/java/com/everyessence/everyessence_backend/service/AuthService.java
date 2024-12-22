package com.everyessence.everyessence_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.everyessence.everyessence_backend.model.User;
import com.everyessence.everyessence_backend.repository.UserRepository;
import com.everyessence.everyessence_backend.util.JWTTokenProvider;
import com.everyessence.everyessence_backend.constants.Messages;




@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTTokenProvider JwtTokenProvider;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // method for register
    public String RegisterUser(User user){
        
        User ourUser = userRepository.findByEmail(user.getEmail());
        if(ourUser != null){
            throw new RuntimeException(Messages.EMAIL_ALREADY_EXISTS);
        }
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(newUser);

        return Messages.USER_REGISTERED_SUCCESSFULLY;
    }
    // method for login
    public String AuthenticateUser(User user){
        User ourUser = userRepository.findByEmail(user.getEmail());
        if(ourUser == null){
            throw new RuntimeException(Messages.USER_NOT_FOUND);
        }
        if(!passwordEncoder.matches(user.getPassword(), ourUser.getPassword())){
            throw new RuntimeException(Messages.INVALID_CREDENTIALS);
        }
        String token = JwtTokenProvider.createToken(user.getEmail());
        return token;
    }
    
}
