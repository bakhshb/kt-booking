package com.kiwianatours.ktbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kiwianatours.ktbooking.domain.TourPhoto;
import com.kiwianatours.ktbooking.domain.Tour;

import java.util.List;

public interface TourPhotoRepository extends JpaRepository<TourPhoto, Long>{

	List<TourPhoto> findByTour(Tour tour);
	
	@Query("select p from TourPhoto p where p.tour.id = ?1")
	List<TourPhoto> getTourPhotos (Long id);

}
