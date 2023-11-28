package com.api.socialmediaapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialmedia.auth.api.AuthApi;
import com.socialmedia.auth.api.model.UserCredentials;
import com.socialmedia.auth.api.model.UserToken;


@RestController
@RequestMapping("/api")
public class AuthController implements AuthApi{
	
	@Override
	@PostMapping("/auth")
	public ResponseEntity<UserToken> authenticateUser(UserCredentials userCredentials) {
		return AuthApi.super.authenticateUser(userCredentials);
	}

}
