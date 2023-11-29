/*
 * package com.api.socialmediaapp.service.impl;
 * 
 * import java.util.Optional;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.api.socialmediaapp.config.JWTService; import
 * com.api.socialmediaapp.constant.SocialmediaConstant; import
 * com.api.socialmediaapp.model.User; import
 * com.api.socialmediaapp.repo.UserRepository; import
 * com.api.socialmediaapp.service.AuthService; import
 * com.socialmedia.auth.api.model.UserCredentials; import
 * com.socialmedia.auth.api.model.UserToken;
 * 
 * @Service public class AutheServiceImpl implements AuthService{
 * 
 * 
 * @Autowired JWTService jwtService;
 * 
 * @Autowired UserRepository userRepository;
 * 
 * @Override public ResponseEntity<UserToken> authenticateUser(UserCredentials
 * userCredentials) {
 * 
 * Optional<User> user =
 * userRepository.findByUsername(userCredentials.getUsername());
 * 
 * if(user.isEmpty()) { throw new
 * UsernameNotFoundException(SocialmediaConstant.USER_NOT_FOUND); } UserToken
 * userToken = new UserToken(); userToken.setUsername(user.get().getUsername());
 * userToken.setUserId(user.get().getUser_id());
 * userToken.setAuthToken(jwtService.generateToken(userCredentials.getUsername()
 * )); return new ResponseEntity<UserToken>(userToken,HttpStatus.CREATED); }
 * 
 * }
 */