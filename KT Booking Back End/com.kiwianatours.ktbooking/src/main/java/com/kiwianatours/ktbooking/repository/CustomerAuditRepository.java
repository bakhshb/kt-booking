package com.kiwianatours.ktbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiwianatours.ktbooking.domain.CustomerAudit;
/**
 * Spring Data JPA repository for the CustomerAudit entity.
 */
public interface CustomerAuditRepository extends JpaRepository<CustomerAudit, Long>{

}
