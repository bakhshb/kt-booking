package com.kt.booking.service;

import java.util.List;

import com.kt.booking.model.Tour;

public interface TourService {

	/**
	 * this method return all the tour from database
	 * 
	 * @return List<Tour>
	 */
	public List<Tour> findAll();

	/**
	 * this method look for tour by id from database
	 * 
	 * @param id
	 * @return Tour
	 */
	public Tour findById(Long id);

	/**
	 * this method insert tour into database
	 * 
	 * @param tour
	 * @return
	 */
	public Tour save(Tour tour);
}
