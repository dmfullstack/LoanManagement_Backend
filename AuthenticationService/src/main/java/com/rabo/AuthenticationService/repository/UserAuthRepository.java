package com.rabo.AuthenticationService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rabo.AuthenticationService.model.User;

@Repository
public interface UserAuthRepository extends JpaRepository<User, String> {

	public User findByUserIdAndPassword(String userId, String password);

}
