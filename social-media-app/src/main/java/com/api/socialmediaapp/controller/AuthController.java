/*
 * package com.api.socialmediaapp.controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.api.socialmediaapp.service.AuthService; import
 * com.socialmedia.auth.api.AuthApi; import
 * com.socialmedia.auth.api.model.UserCredentials; import
 * com.socialmedia.auth.api.model.UserToken;
 * 
 * 
 * @RestController
 * 
 * @RequestMapping("/api") public class AuthController implements AuthApi{
 * 
 * 
 * @Autowired AuthService authService;
 * 
 * @Override
 * 
 * @PostMapping("/auth") public ResponseEntity<UserToken>
 * authenticateUser(@RequestBody UserCredentials userCredentials) {
 * 
 * return authService.authenticateUser(userCredentials); }
 * 
 * }
 */