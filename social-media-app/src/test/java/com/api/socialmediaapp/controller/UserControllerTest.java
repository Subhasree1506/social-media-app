package com.api.socialmediaapp.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.api.socialmediaapp.constant.SocialmediaConstant;
import com.api.socialmediaapp.error.BadRequestException;
import com.api.socialmediaapp.service.UserService;
import com.socialmedia.user.api.model.UserDetailsResponse;
import com.socialmedia.user.api.model.UserRegistrationRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testCreateUser_ValidRequest_Success() {
        // Arrange
        UserRegistrationRequest validRequest = new UserRegistrationRequest();
        validRequest.setEmail("user@example.com");
        validRequest.setPassword("Abcd@1234");

        when(userService.registerUser(validRequest)).thenReturn(ResponseEntity.ok().build());

        // Act
        ResponseEntity<Void> response = userController.createUser(validRequest);

        // Assert
        assertEquals(ResponseEntity.ok().build(), response);
        logger.info("User registration successful");
    }

    @Test
    void testCreateUser_InvalidEmail_ExceptionThrown() {
        // Arrange
        UserRegistrationRequest invalidEmailRequest = new UserRegistrationRequest();
        invalidEmailRequest.setEmail("invalid-email");
        invalidEmailRequest.setPassword("Abcd@1234");

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> userController.createUser(invalidEmailRequest));

        assertEquals(SocialmediaConstant.INVALID_EMAIL_FORMAT, exception.getMessage());
        verifyNoInteractions(userService);
        logger.error("User registration failed due to invalid email format");
    }

    @Test
    void testCreateUser_InvalidPassword_ExceptionThrown() {
        // Arrange
        UserRegistrationRequest invalidPasswordRequest = new UserRegistrationRequest();
        invalidPasswordRequest.setEmail("user@example.com");
        invalidPasswordRequest.setPassword("invalidpassword");

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> userController.createUser(invalidPasswordRequest));

        assertEquals(SocialmediaConstant.INVALID_PASSWORD_FORMAT, exception.getMessage());
        verifyNoInteractions(userService);
        logger.error("User registration failed due to invalid password format");
    }

    @Test
    void testGetUserById_ValidUserId_Success() {
        // Arrange
        String userId = "123";
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse();

        when(userService.getUser(userId)).thenReturn(ResponseEntity.ok(userDetailsResponse));

        // Act
        ResponseEntity<UserDetailsResponse> response = userController.getUserById(userId);

        // Assert
        assertEquals(ResponseEntity.ok(userDetailsResponse), response);
        logger.info("User details retrieved successfully");
    }
}
