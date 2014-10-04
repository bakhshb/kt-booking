package com.kiwianatours.ktbooking.repository;

import com.kiwianatours.ktbooking.domain.TourSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.lang.String;

/**
 * Spring Data JPA repository for the TourSchedule entity.
 */
public interface TourScheduleRepository extends JpaRepository<TourSchedule, Long> {

}
