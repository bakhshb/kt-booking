package com.kiwianatours.ktbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kiwianatours.ktbooking.domain.TourBooking;
import com.kiwianatours.ktbooking.domain.Booking;

import java.util.List;

import com.kiwianatours.ktbooking.domain.TourSchedule;

/**
 * Spring Data JPA repository for the Tour Booking entity.
 */
public interface TourBookingRepository extends JpaRepository<TourBooking, Long> {

	@Query ("select t from TourBooking t join t.booking b where b.id = ?1")
	TourBooking findTourBookingByBooking (Long bookingId);
	
	@Query("select t from TourBooking t join t.booking b where b.customer.id = ?1")
	List<TourBooking> findCustomerBookingByCustomer (Long customerId);

	@Query("select t from TourBooking t join t.booking b where b.status =1 and t.tourSchedule.id = ?1")
	List<TourBooking> findApprovedBookingByTourSchedule(Long tourScheduleId);
}
