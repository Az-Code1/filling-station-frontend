package io.aljavap.fillingStation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.aljavap.fillingStation.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	Optional<User> findByPhoneNumber(String phoneNumber);

	Optional<User> findByUsernameOrEmailOrPhoneNumberAndPassword(String username, String email, String phoneNumber,
			String password);
    
}