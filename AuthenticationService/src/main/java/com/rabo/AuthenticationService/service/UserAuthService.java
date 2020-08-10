package com.rabo.AuthenticationService.service;

import com.rabo.AuthenticationService.exception.UserAlreadyExistsException;
import com.rabo.AuthenticationService.exception.UserNotFoundException;
import com.rabo.AuthenticationService.model.User;

public interface UserAuthService {

	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;

	boolean saveUser(User user) throws UserAlreadyExistsException;
}
