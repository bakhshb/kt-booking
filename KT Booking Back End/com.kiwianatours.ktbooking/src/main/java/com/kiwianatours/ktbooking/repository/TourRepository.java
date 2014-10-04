package com.kiwianatours.ktbooking.repository;

import com.kiwianatours.ktbooking.domain.Tour;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.String;
import java.util.List;

/**
 * Spring Data JPA repository for the Tour entity.
 */
public interface TourRepository extends JpaRepository<Tour, Long> {
	
	Tour findByName(String name);

}
