package com.kiwianatours.ktbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiwianatours.ktbooking.domain.TourAudit;

/**
 * Spring Data JPA repository for the TourAudit entity.
 */
public interface TourAuditRepository extends JpaRepository<TourAudit, Long> {

}
