package com.rabo.AuthenticationService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabo.AuthenticationService.exception.UserAlreadyExistsException;
import com.rabo.AuthenticationService.exception.UserNotFoundException;
import com.rabo.AuthenticationService.model.User;
import com.rabo.AuthenticationService.repository.UserAuthRepository;

@Service
public class UserAuthServiceImpl implements UserAuthService {

	private UserAuthRepository userAuthRepository;

	@Autowired
	public UserAuthServiceImpl(UserAuthRepository userAuthRepository) {

		this.userAuthRepository = userAuthRepository;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user = userAuthRepository.findByUserIdAndPassword(userId, password);
		if (null != user) {
			return user;
		}
		throw new UserNotFoundException("NOT_FOUND");
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		Optional opt = userAuthRepository.findById(user.getUserId());

		if (opt.isPresent()) {

			throw new UserAlreadyExistsException("UserAlreadyExist");

		}
		userAuthRepository.save(user);
		return true;
	}

}
