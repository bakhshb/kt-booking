package com.kt.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kt.booking.model.TourInfo;
import com.kt.booking.service.TourInfoService;

@RestController
@RequestMapping("/rest/tourinfo")
public class TourInfoCtrl {

	@Autowired
	private TourInfoService tourInfoService;

	/**
	 * this method list all the tourInfo
	 * @return List<TourInfo>
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<TourInfo> findAll() {
		return (List<TourInfo>) tourInfoService.findAll();
	}

	/**
	 * this method look for tourInfo by id
	 * @param id
	 * @return Tour and HttpStatus
	 */
	@RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
	public ResponseEntity<TourInfo> findById(@PathVariable Long id) {

		return new ResponseEntity<TourInfo>(tourInfoService.findById(id),
				HttpStatus.OK);
	}

	/**
	 * this method insert tourInfo
	 * @param tourinfo
	 * @return TourInfo and HttpStatus
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<TourInfo> save(@RequestBody final TourInfo TourInfo) {
		return new ResponseEntity<TourInfo>(tourInfoService.save(TourInfo),
				HttpStatus.OK);
	}

}
