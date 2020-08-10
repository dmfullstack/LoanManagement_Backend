package com.rabo.UserProfileService.service;

import org.springframework.stereotype.Service;

import com.rabo.UserProfileService.exception.UserProfileAlreadyExistsException;
import com.rabo.UserProfileService.exception.UserProfileNotFoundException;
import com.rabo.UserProfileService.model.UserProfile;

public interface UserProfileService {

	UserProfile registerUser(UserProfile user) throws UserProfileAlreadyExistsException;

	UserProfile updateUser(String userId, UserProfile user) throws UserProfileNotFoundException;

	boolean deleteUser(String userId) throws UserProfileNotFoundException;

	UserProfile getUserById(String userId) throws UserProfileNotFoundException;

}
