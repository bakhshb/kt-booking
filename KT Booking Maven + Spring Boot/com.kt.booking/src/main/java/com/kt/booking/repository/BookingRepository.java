package com.kt.booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kt.booking.model.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

}
