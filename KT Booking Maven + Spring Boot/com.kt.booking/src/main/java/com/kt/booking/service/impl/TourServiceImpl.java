package com.kt.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.booking.model.Tour;
import com.kt.booking.repository.TourRepository;
import com.kt.booking.service.TourService;

@Service
public class TourServiceImpl implements TourService{

	@Autowired
	private TourRepository tourRepository;
	
	@Override
	@Transactional
	public List<Tour> findAll() {
		return (List<Tour>) tourRepository.findAll();
	}

	@Override
	@Transactional
	public Tour findById(Long id) {
		return tourRepository.findOne(id);
	}

	@Override
	@Transactional
	public Tour save(Tour tour) {
		return tourRepository.save(tour);
	}
}
