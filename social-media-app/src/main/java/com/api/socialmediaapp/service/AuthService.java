package com.api.socialmediaapp.service;

import org.springframework.http.ResponseEntity;

import com.socialmedia.auth.api.model.UserCredentials;
import com.socialmedia.auth.api.model.UserToken;

public interface AuthService {

	public ResponseEntity<UserToken> authenticateUser(UserCredentials userCredentials);
}
