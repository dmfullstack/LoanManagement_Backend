package com.rabo.UserProfileService.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabo.UserProfileService.exception.UserProfileAlreadyExistsException;
import com.rabo.UserProfileService.exception.UserProfileNotFoundException;
import com.rabo.UserProfileService.model.UserProfile;
import com.rabo.UserProfileService.service.UserProfileService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class UserProfileController {

	private UserProfileService userService;

	@Autowired
	public UserProfileController(UserProfileService userProfileService) {
		this.userService = userProfileService;
	}

	@PostMapping("/user")
	public ResponseEntity<?> registerUserProfile(@RequestBody UserProfile user) {

		ResponseEntity<?> entity;
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			if (!user.getUserId().isEmpty() && !user.getEmail().isEmpty() && !user.getFirstName().isEmpty()
					&& !user.getLastName().isEmpty() && !user.getContact().isEmpty()) {
				userService.registerUser(user);
				map.put("message", "User Registration Successfull");
				entity = new ResponseEntity<>(map, HttpStatus.CREATED);
			} else {
				map.put("message", "fields should not be empty");
				entity = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
			}
		} catch (UserProfileAlreadyExistsException e) {
			map.put("message", "User is already registered");
			entity = new ResponseEntity<>(map, HttpStatus.CONFLICT);
		}
		return entity;

	}

	@PutMapping("/userprofile/{userId}")
	public ResponseEntity<?> updateUserProfile(@PathVariable("userId") String userId, @RequestBody UserProfile user) {

		try {
			UserProfile updatedUser = userService.updateUser(userId, user);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} catch (UserProfileNotFoundException e) {
			return new ResponseEntity<>("User Profile Not Found", HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/userprofile/{userId}")
	public ResponseEntity<?> deleteUserProfile(@PathVariable("userId") String userId) {

		ResponseEntity<?> entity;
		try {
			boolean isDeleted = userService.deleteUser(userId);
			entity = new ResponseEntity<>("UserProfile Deleted successfully", HttpStatus.OK);
		} catch (UserProfileNotFoundException e) {
			entity = new ResponseEntity<>("User profile Not Found", HttpStatus.NOT_FOUND);
		}
		return entity;
	}

	@GetMapping("/userprofile/{userId}")
	public ResponseEntity<?> getUserProfileById(@PathVariable("userId") String userId) {

		ResponseEntity<?> entity;
//
//		HashMap<String, List<UserProfile>> map = new HashMap<>();
//
//		List<UserProfile> listUser = new LinkedList<>();
//		UserProfile user = null;
//		try {
//			user = userService.getUserById(userId);
//
//			listUser.add(user);
//			map.put("profile", listUser);
//			entity = new ResponseEntity<>(map, HttpStatus.OK);
//		} catch (UserProfileNotFoundException e) {
//			listUser.add(user);
//			map.put("profile", listUser);
//			entity = new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
//		}
//		return entity;
		try {
		UserProfile user = userService.getUserById(userId);

			return new ResponseEntity<>(user, HttpStatus.OK);

		} catch (UserProfileNotFoundException e) {
			return new ResponseEntity<>("Profile NOT FOUND for this userId", HttpStatus.NOT_FOUND);

		}
	}

}
