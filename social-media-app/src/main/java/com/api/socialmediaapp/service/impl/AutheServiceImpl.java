package com.api.socialmediaapp.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.socialmediaapp.service.AuthService;
import com.socialmedia.auth.api.model.UserCredentials;
import com.socialmedia.auth.api.model.UserToken;

@Service
public class AutheServiceImpl implements AuthService{

	@Override
	public ResponseEntity<UserToken> authenticateUser(UserCredentials userCredentials) {
		// TODO Auto-generated method stub
		return null;
	}

}
