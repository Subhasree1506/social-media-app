package com.api.socialmediaapp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.socialmediaapp.error.NotFoundException;
import com.api.socialmediaapp.error.UserAlreadyExistsException;
import com.api.socialmediaapp.model.User;
import com.api.socialmediaapp.repo.UserRepository;
import com.api.socialmediaapp.service.UserService;
import com.socialmedia.user.api.model.UserDetailsResponse;
import com.socialmedia.user.api.model.UserDetailsResponse.GenderEnum;
import com.socialmedia.user.api.model.UserRegistrationRequest;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	/*
	 * @Autowired PasswordEncoder passwordEncoder;
	 */

	/*
	 * @Autowired PasswordEncoder passwordEncoder;
	 */
	@Override
	public ResponseEntity<Void> registerUser(UserRegistrationRequest user) {

		if (userRepository.existsByUsername(user.getUsername())) {
			throw new UserAlreadyExistsException("username", user.getUsername());
		}

		if (userRepository.existsByEmail(user.getEmail())) {
			throw new UserAlreadyExistsException("email", user.getEmail());
		}

		User newUser = User.builder().username(user.getUsername()).password_hash(/* passwordEncoder.encode( */user
				.getPassword())/* ) */
				.date_of_birth(user.getDateOfBirth()).bio(user.getBio()).email(user.getEmail())
				.full_name(user.getFullname()).gender(user.getGender().getValue()).build();

		userRepository.save(newUser);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<UserDetailsResponse> getUser(String userName) {

		Optional<User> userResult = userRepository.findByUsername(userName);
		UserDetailsResponse response;
		if (userResult.isPresent()) {
			User user = userResult.get();
			response = new UserDetailsResponse();
			response.setBio(user.getBio());
			response.setDateOfBirth(user.getDate_of_birth());
			response.setEmail(user.getEmail());
			response.setFullname(user.getFull_name());
			response.setGender(GenderEnum.fromValue(user.getGender()));
			response.setUsername(user.getUsername());
			response.setUserID(user.getUser_id());

		} else {
			throw new NotFoundException("User Not Found");
		}

		return new ResponseEntity<UserDetailsResponse>(response, HttpStatus.OK);
	}

}
