package com.kt.booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kt.booking.model.Tour;

@Repository
public interface TourRepository extends CrudRepository<Tour, Long>{

}
