package com.kiwianatours.ktbooking.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kiwianatours.ktbooking.domain.TourSchedule;
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
	private TourScheduleService tourScheduleService;
	

	/**
	 * POST /rest/tourschedules -> Create a new tourSchedule.
	 */
	@RequestMapping(value = "/rest/tourschedules", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Secured(AuthoritiesConstants.ADMIN)
	@Timed
	public void create(@RequestBody TourScheduleDTO tourScheduleDTO) {
		log.debug("REST request to save TourSchedule : {}", tourScheduleDTO);
		tourScheduleService.createTourSchedule(tourScheduleDTO.getTour().getId(), tourScheduleDTO.getId(), tourScheduleDTO.getDepartureDate(),
				tourScheduleDTO.getReturnDate(), tourScheduleDTO.getPrice(),tourScheduleDTO.getTime(), tourScheduleDTO.getDay());
	}

	/**
	 * GET /rest/tourschedules -> get all the tourSchedules.
	 */
	@RequestMapping(value = "/rest/tourschedules", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<TourSchedule> getAll() {
		log.debug("REST request to get all TourSchedules");
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
		TourSchedule tourSchedule = tourScheduleRepository.findOne(id);
		if (tourSchedule == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(tourSchedule, HttpStatus.OK);
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
