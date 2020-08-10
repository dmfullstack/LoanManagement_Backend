package com.rabo.AuthenticationService.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rabo.AuthenticationService.exception.UserAlreadyExistsException;
import com.rabo.AuthenticationService.exception.UserNotFoundException;
import com.rabo.AuthenticationService.model.User;
import com.rabo.AuthenticationService.service.UserAuthService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserAuthController {

	private UserAuthService userAuthService;

	@Autowired
	public UserAuthController(UserAuthService userAuthService) {
		this.userAuthService = userAuthService;
	}

	@PostMapping("/api/v1/auth/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
		try {

			return new ResponseEntity<>(userAuthService.saveUser(user), HttpStatus.CREATED);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

	}

	@PostMapping("/api/v1/auth/login")
	public ResponseEntity<?> login(@RequestBody User user) {

		String jwtToken = null;
		try {
			User userObj = userAuthService.findByUserIdAndPassword(user.getUserId(), user.getPassword());
			if (userObj != null) {

				jwtToken = Jwts.builder().setSubject(user.getUserId()).setIssuedAt(new Date())
						.signWith(SignatureAlgorithm.HS256, "secretKey").compact();

				return new ResponseEntity<>(jwtToken, HttpStatus.OK);

			}
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}
