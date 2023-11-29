package com.api.socialmediaapp.service;

import org.springframework.http.ResponseEntity;

import com.socialmedia.user.api.model.UserRegistrationRequest;
import com.socialmedia.user.api.model.UserDetailsResponse;


public interface UserService {

    ResponseEntity<Void> registerUser(UserRegistrationRequest user);
    
    ResponseEntity<UserDetailsResponse> getUser(String userName);


}
