package com.kiwianatours.ktbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiwianatours.ktbooking.domain.TourScheduleAudit;

/**
 * Spring Data JPA repository for the TourScheduleAudit entity.
 */
public interface TourScheduleAuditRepository extends JpaRepository<TourScheduleAudit, Long>{

}
