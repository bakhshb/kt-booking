package com.kiwianatours.ktbooking.repository;

import com.kiwianatours.ktbooking.domain.User;

import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, String> {
    
    @Query("select u from User u where u.activationKey = ?1")
    User getUserByActivationKey(String activationKey);
    
    @Query("select u from User u where u.activated = false and u.createdDate > ?1")
    List<User> findNotActivatedUsersByCreationDateBefore(LocalDate localDate);

    @Query("select u from User u where u.login =?1 or u.email =?2")
    User getUserByLoginAndEmail (String login, String email);
}
