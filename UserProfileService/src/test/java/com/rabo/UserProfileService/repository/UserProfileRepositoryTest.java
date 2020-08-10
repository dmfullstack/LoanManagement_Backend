package com.rabo.UserProfileService.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.is;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rabo.UserProfileService.model.UserProfile;
import com.rabo.UserProfileService.repository.UserProfileRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserProfileRepositoryTest {
	@Autowired
	private UserProfileRepository userProfileRepository;
	private UserProfile userProfile;

	@BeforeEach
	public void setUp() throws Exception {
		userProfile = new UserProfile();
		userProfile.setUserId("Jhon123");
		userProfile.setFirstName("Jhon");
		userProfile.setLastName("Simon");
		userProfile.setContact("9898989898");
		userProfile.setEmail("Jhon@gmail.com");
		userProfile.setCreatedAt();
	}

	@AfterEach
	public void tearDown() throws Exception {

		userProfileRepository.deleteAll();

	}

	@Test
	public void registerUserTest() {

		userProfileRepository.save(userProfile);
		UserProfile fetcheduser = userProfileRepository.findById("Jhon123").get();

		assertEquals(userProfile.getUserId(), fetcheduser.getUserId());

	}

	@Test
	public void deleteUserTest() {
		userProfileRepository.save(userProfile);
		UserProfile fetcheduser = userProfileRepository.findById("Jhon123").get();
		assertEquals("Jhon123", fetcheduser.getUserId());
		userProfileRepository.delete(fetcheduser);
		try {
			userProfileRepository.findById("Jhon123").get();
		} catch (Exception exception) {
			assertEquals(NoSuchElementException.class, exception.getClass());
		}

	}

	@Test
	public void updateUserTest() {
		userProfileRepository.save(userProfile);
		UserProfile fetcheduser = userProfileRepository.findById("Jhon123").get();
		fetcheduser.setContact("987654321");
		userProfileRepository.save(fetcheduser);
		fetcheduser = userProfileRepository.findById("Jhon123").get();
		assertEquals("987654321", fetcheduser.getContact());
	}

	@Test
	public void getUserByIdTest() {
		userProfileRepository.save(userProfile);
		UserProfile fetcheduser = userProfileRepository.findById("Jhon123").get();
		assertEquals(userProfile.getUserId(), fetcheduser.getUserId());

	}

}
