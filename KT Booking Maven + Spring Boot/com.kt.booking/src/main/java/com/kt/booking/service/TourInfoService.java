package com.kt.booking.service;

import java.util.List;

import com.kt.booking.model.TourInfo;

public interface TourInfoService {
	/**
	 * this method return all the tourInfo from database
	 * 
	 * @return List<TourInfo>
	 */
	public List<TourInfo> findAll();

	/**
	 * this method look for tourInfo by id from database
	 * 
	 * @param id
	 * @return TourInfo
	 */
	public TourInfo findById(Long id);

	/**
	 * this method insert tour into database
	 * 
	 * @param tourInfo
	 * @return
	 */
	public TourInfo save(TourInfo tourInfo);
}
