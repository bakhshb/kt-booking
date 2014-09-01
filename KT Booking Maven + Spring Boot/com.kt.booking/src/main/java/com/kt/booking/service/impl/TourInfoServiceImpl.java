package com.kt.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.booking.model.TourInfo;
import com.kt.booking.repository.TourInfoRepository;
import com.kt.booking.service.TourInfoService;

@Service
public class TourInfoServiceImpl implements TourInfoService {

	@Autowired
	private TourInfoRepository tourInfoRepository;

	@Override
	@Transactional
	public List<TourInfo> findAll() {
		return (List<TourInfo>) tourInfoRepository.findAll();
	}

	@Override
	@Transactional
	public TourInfo findById(Long id) {
		return tourInfoRepository.findOne(id);
	}

	@Override
	@Transactional
	public TourInfo save(TourInfo tourInfo) {
		return tourInfoRepository.save(tourInfo);
	}

}
