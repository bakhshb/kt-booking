package com.kiwianatours.ktbooking.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kiwianatours.ktbooking.domain.TourSchedule;
import com.kiwianatours.ktbooking.domain.TourScheduleAudit;
import com.kiwianatours.ktbooking.repository.TourScheduleAuditRepository;
import com.kiwianatours.ktbooking.repository.TourScheduleRepository;
import com.kiwianatours.ktbooking.security.AuthoritiesConstants;
import com.kiwianatours.ktbooking.service.TourScheduleService;
import com.kiwianatours.ktbooking.web.rest.dto.TourScheduleDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * REST controller for managing TourSchedule.
 */
@RestController
@RequestMapping("/app")
public class TourScheduleResource {

	private final Logger log = LoggerFactory.getLogger(TourScheduleResource.class);

	@Inject
	private TourScheduleRepository tourScheduleRepository;

	@Inject
	private TourScheduleService tourinfoService;
	
	@Inject
	private TourScheduleAuditRepository tourScheduleAuditRepository;

	/**
	 * POST /rest/tourschedules -> Create a new tourSchedule.
	 */
	@RequestMapping(value = "/rest/tourschedules", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Secured(AuthoritiesConstants.ADMIN)
	@Timed
	public void create(@RequestBody TourScheduleDTO tourinfoDTO) {
		log.debug("REST request to save TourSchedule : {}", tourinfoDTO);
		tourinfoService.createTourSchedule(tourinfoDTO.getTour().getId(), tourinfoDTO.getId(), tourinfoDTO.getDepartureDate(),
				tourinfoDTO.getReturnDate(), tourinfoDTO.getPrice(),tourinfoDTO.getTime(), tourinfoDTO.getDay());
	}

	/**
	 * GET /rest/tourschedules -> get all the tourSchedules.
	 */
	@RequestMapping(value = "/rest/tourschedules", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<TourSchedule> getAll() {
		log.debug("REST request to get all TourSchedules");
		for(TourScheduleAudit tourS: tourScheduleAuditRepository.findAll()){
			System.err.println(tourS.getDepartureDate() +" " + tourS.getAttending());
		}
		return tourScheduleRepository.findAll();
	}

	/**
	 * GET /rest/tourschedules/:id -> get the "id" tourSchedule.
	 */
	@RequestMapping(value = "/rest/tourschedules/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<TourSchedule> get(@PathVariable Long id,
			HttpServletResponse response) {
		log.debug("REST request to get TourSchedule : {}", id);
		TourSchedule tourinfo = tourScheduleRepository.findOne(id);
		if (tourinfo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(tourinfo, HttpStatus.OK);
	}

	/**
	 * DELETE /rest/tourschedules/:id -> delete the "id" tourSchedule.
	 */
	@RequestMapping(value = "/rest/tourschedules/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Secured(AuthoritiesConstants.ADMIN)
	@Timed
	public void delete(@PathVariable Long id) {
		log.debug("REST request to delete TourSchedule : {}", id);
		tourScheduleRepository.delete(id);
	}
		
}
