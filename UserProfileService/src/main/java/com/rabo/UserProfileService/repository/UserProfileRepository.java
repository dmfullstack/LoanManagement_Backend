package com.rabo.UserProfileService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.rabo.UserProfileService.model.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
	List<UserProfile> findByEmail(String email);
}
