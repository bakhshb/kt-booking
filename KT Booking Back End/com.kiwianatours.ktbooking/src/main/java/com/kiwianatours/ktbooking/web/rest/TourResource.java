package com.kiwianatours.ktbooking.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kiwianatours.ktbooking.domain.Tour;
import com.kiwianatours.ktbooking.domain.TourPhoto;
import com.kiwianatours.ktbooking.repository.TourPhotoRepository;
import com.kiwianatours.ktbooking.repository.TourRepository;
import com.kiwianatours.ktbooking.repository.TourScheduleRepository;
import com.kiwianatours.ktbooking.security.AuthoritiesConstants;
import com.kiwianatours.ktbooking.service.TourPhotoService;

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
 * REST controller for managing Tour.
 */
@RestController
@RequestMapping("/app")
public class TourResource {

    private final Logger log = LoggerFactory.getLogger(TourResource.class);

    @Inject
    private TourRepository tourRepository;
    
    @Inject
    private TourScheduleRepository tourScheduleRepository;
    
    @Inject
	private TourPhotoRepository tourPhotoRepository;
	
	@Inject
	private TourPhotoService tourPhotoService;

    /**
     * POST  /rest/tours -> Create a new tour.
     */
    @RequestMapping(value = "/rest/tours",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured(AuthoritiesConstants.ADMIN)
    @Timed
    public void create(@RequestBody Tour tour) {
        log.debug("REST request to save Tour : {}", tour);
        tourRepository.save(tour);
    }

    /**
     * GET  /rest/tours -> get all the tours.
     */
    @RequestMapping(value = "/rest/tours",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Tour> getAll() {
        log.debug("REST request to get all Tours");
        return tourRepository.findAll();
    }

    /**
     * GET  /rest/tours/:id -> get the "id" tour.
     */
    @RequestMapping(value = "/rest/tours/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Tour> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Tour : {}", id);
        Tour tour = tourRepository.findOne(id);
        if (tour == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/tours/:id -> delete the "id" tour.
     */
    @RequestMapping(value = "/rest/tours/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured(AuthoritiesConstants.ADMIN)
    @Timed
    public  ResponseEntity<Tour> delete(@PathVariable Long id){
        log.debug("REST request to delete Tour : {}", id);
		if (tourScheduleRepository.findAllTourScheduleByTourId(id).size() == 0){
			List<TourPhoto> tourPhotos = tourPhotoRepository.findTourPhotosByTourId(id);
			if (tourPhotos.size() != 0){
				for (TourPhoto tourPhoto: tourPhotos){
					tourPhotoService.deletePhoto(tourPhoto.getId());
				}
				tourRepository.delete(id);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			tourRepository.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
