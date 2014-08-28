package com.kt.booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kt.booking.model.TourBooking;

@Repository
public interface TourBookingRepository extends CrudRepository<TourBooking, Long> {

}
