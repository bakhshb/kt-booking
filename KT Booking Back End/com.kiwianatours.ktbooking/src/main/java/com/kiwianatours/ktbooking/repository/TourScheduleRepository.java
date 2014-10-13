package com.kiwianatours.ktbooking.repository;

import java.util.List;

import com.kiwianatours.ktbooking.domain.TourSchedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Spring Data JPA repository for the TourSchedule entity.
 */
public interface TourScheduleRepository extends JpaRepository<TourSchedule, Long> {

	@Query ("select t from TourSchedule t where t.tour.id =?1 ")
	List<TourSchedule> findAllTourScheduleByTourId (Long tourId);
}
