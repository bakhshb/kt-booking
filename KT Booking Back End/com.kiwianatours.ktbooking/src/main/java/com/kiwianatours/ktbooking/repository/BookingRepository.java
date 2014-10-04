package com.kiwianatours.ktbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiwianatours.ktbooking.domain.Booking;
import com.kiwianatours.ktbooking.domain.Customer;
import java.util.List;

/**
 * Spring Data JPA repository for the Booking entity.
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByCustomer(Customer customer);
}
