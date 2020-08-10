package com.rabo.UserProfileService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabo.UserProfileService.exception.UserProfileAlreadyExistsException;
import com.rabo.UserProfileService.exception.UserProfileNotFoundException;
import com.rabo.UserProfileService.model.UserProfile;
import com.rabo.UserProfileService.repository.UserProfileRepository;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	private UserProfileRepository userProfileRepository;

	@Autowired
	public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
		super();
		this.userProfileRepository = userProfileRepository;
	}

	public UserProfile registerUser(UserProfile user) throws UserProfileAlreadyExistsException {
		try {
			user.setCreatedAt();
			if (userProfileRepository.findByEmail(user.getEmail()).size() > 0) {
				throw new UserProfileAlreadyExistsException("Error");
			} else if (userProfileRepository.save(user) == null) {
				throw new UserProfileAlreadyExistsException("Error");
			} else {
				return user;
			}
		} catch (Exception e) {
			throw new UserProfileAlreadyExistsException("Error");
		}
	}

	@Override
	public UserProfile updateUser(String userId, UserProfile user) throws UserProfileNotFoundException {
		Optional<UserProfile> opt = this.userProfileRepository.findById(user.getUserId());
		if (opt.isPresent()) {
			try {
				if (user.getFirstName().isEmpty()) {
					user.setFirstName(opt.get().getFirstName());
				}
				if (user.getLastName().isEmpty()) {
					user.setLastName(opt.get().getLastName());
				}
				if (user.getEmail().isEmpty()) {
					user.setEmail(opt.get().getEmail());
				}
				if (user.getContact().isEmpty()) {
					user.setContact(opt.get().getContact());
				}
				user.setUserId(userId);
				userProfileRepository.save(user);
				return user;
			} catch (Exception e) {
				return null;
			}
		}
		throw new UserProfileNotFoundException("User Not Found");
	}

	@Override
	public boolean deleteUser(String userId) throws UserProfileNotFoundException {
		Optional<UserProfile> opt = this.userProfileRepository.findById(userId);
		if (opt.isPresent()) {
			this.userProfileRepository.deleteById(userId);
			return true;
		}
		return false;
	}

	@Override
	public UserProfile getUserById(String userId) throws UserProfileNotFoundException {
		Optional<UserProfile> opt = this.userProfileRepository.findById(userId);

		if (opt.isPresent()) {

			return opt.get();
		}

		throw new UserProfileNotFoundException("User Not Found");
	}
}
