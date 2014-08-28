package com.kt.booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kt.booking.model.TourInfo;

@Repository
public interface TourInfoRepository extends CrudRepository<TourInfo, Long>{

}
