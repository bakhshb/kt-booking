package com.kiwianatours.ktbooking.repository;

import com.kiwianatours.ktbooking.domain.Tour;

import org.springframework.data.jpa.repository.JpaRepository;
import java.lang.String;

/**
 * Spring Data JPA repository for the Tour entity.
 */
public interface TourRepository extends JpaRepository<Tour, Long> {
	
	Tour findByName(String name);

}
