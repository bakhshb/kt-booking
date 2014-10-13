package com.kiwianatours.ktbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kiwianatours.ktbooking.domain.TourBooking;
import java.util.List;

/**
 * Spring Data JPA repository for the Tour Booking entity.
 */
public interface TourBookingRepository extends JpaRepository<TourBooking, Long> {

	@Query ("select t from TourBooking t join t.booking b where b.id = ?1")
	TourBooking findTourBookingByBooking (Long bookingId);
	
	@Query("select t from TourBooking t join t.booking b where b.customer.id = ?1")
	List<TourBooking> findCustomerBookingByCustomer (Long customerId);

	@Query("select t from TourBooking t join t.booking b where (b.status =1 OR b.status =2 ) AND t.tourSchedule.id = ?1")
	List<TourBooking> findApprovedBookingByTourSchedule(Long tourScheduleId);
	
	@Query("select t from TourBooking t join t.booking b where t.tourSchedule.id = ?1")
	List<TourBooking> findAllBookingByTourSchedule(Long tourScheduleId);
}
