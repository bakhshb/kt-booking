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

import com.kt.booking.model.Tour;
import com.kt.booking.service.TourService;

@RestController
@RequestMapping("/rest/tour")
public class TourCtrl {

	@Autowired
	private TourService tourService;

	/**
	 * this method list all the tour
	 * 
	 * @return List<Tour>
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Tour> findAll() {
		return (List<Tour>) tourService.findAll();
	}

	/**
	 * this method look for tour by id
	 * 
	 * @param id
	 * @return Tour and HttpStatus
	 */
	@RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
	public ResponseEntity<Tour> findById(@PathVariable Long id) {

		return new ResponseEntity<Tour>(tourService.findById(id), HttpStatus.OK);
	}

	/**
	 * this method insert tour
	 * 
	 * @param tour
	 * @return Tour and HttpStatus
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Tour> save(@RequestBody final Tour tour) {
		return new ResponseEntity<Tour>(tourService.save(tour), HttpStatus.OK);
	}

}
