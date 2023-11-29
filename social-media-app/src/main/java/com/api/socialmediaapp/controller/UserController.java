package com.api.socialmediaapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.socialmediaapp.constant.SocialmediaConstant;
import com.api.socialmediaapp.error.BadRequestException;
import com.api.socialmediaapp.service.UserService;
import com.socialmedia.user.api.UserApi;
import com.socialmedia.user.api.model.UserDetailsResponse;
import com.socialmedia.user.api.model.UserRegistrationRequest;

@RestController
@RequestMapping("/api")
@Validated
public class UserController implements UserApi {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Override
    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        // Email validation
        if (!isValidEmail(userRegistrationRequest.getEmail())) {
            logger.error("Invalid email format: {}", userRegistrationRequest.getEmail());
            throw new BadRequestException(SocialmediaConstant.INVALID_EMAIL_FORMAT);
        }

        // Password validation
        if (!isValidPassword(userRegistrationRequest.getPassword())) {
            logger.error("Invalid password format");
            throw new BadRequestException(SocialmediaConstant.INVALID_PASSWORD_FORMAT);
        }

        return userService.registerUser(userRegistrationRequest);
    }

    private boolean isValidEmail(String email) {
        boolean isValid = email != null && email.contains("@");
        if (!isValid) {
            logger.warn("Email validation failed: {}", email);
        }
        return isValid;
    }

    private boolean isValidPassword(String password) {
        // Checking password for alphanumeric, min 8 characters,
        // at least one capital and one small letter, and one special character (@ or #
        // or $)
        String regex = SocialmediaConstant.PASSWORD_REGEX;
        boolean isValid = password != null && password.matches(regex);
        if (!isValid) {
            logger.warn("Password validation failed");
        }
        return isValid;
    }

    @Override
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDetailsResponse> getUserById(@PathVariable String userId) {
        return userService.getUser(userId);
    }
}
