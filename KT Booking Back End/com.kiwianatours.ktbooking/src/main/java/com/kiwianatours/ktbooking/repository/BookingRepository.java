package com.kiwianatours.ktbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kiwianatours.ktbooking.domain.Booking;
import com.kiwianatours.ktbooking.domain.Customer;

import java.util.List;

/**
 * Spring Data JPA repository for the Booking entity.
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByCustomer(Customer customer);
	
	@Query("select b from Booking b where b.customer.id =?1")
	List<Booking> findAllBookingByCustomer(Long customerId);
}

