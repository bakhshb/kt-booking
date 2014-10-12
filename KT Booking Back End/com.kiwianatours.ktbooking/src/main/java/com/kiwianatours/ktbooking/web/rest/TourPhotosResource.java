package com.kiwianatours.ktbooking.web.rest;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codahale.metrics.annotation.Timed;
import com.kiwianatours.ktbooking.domain.TourPhoto;
import com.kiwianatours.ktbooking.repository.TourPhotoRepository;
import com.kiwianatours.ktbooking.service.TourPhotoService;
import com.kiwianatours.ktbooking.web.rest.dto.TourPhotoDTO;

/**
 * REST controller for managing TourPhoto.
 */
@RestController
@RequestMapping("/app")
public class TourPhotosResource {

	private final Logger log = LoggerFactory
			.getLogger(TourPhotosResource.class);

	@Inject
	private TourPhotoRepository tourPhotoRepository;
	
	@Inject
	private TourPhotoService tourPhotoService;
	
	/**
	 * POST /rest/tourphotos -> Upload a new upload photos.
	 */
	@RequestMapping(value = "/rest/tourphotos", method = RequestMethod.POST)
	@Timed
	public void upload(@RequestBody MultipartFile file) {
		log.debug("REST request to save photo : {}", file);
		tourPhotoService.uploadPhotos(file);
		
	}
	
	/**
	 * PUT /rest/tourphotos -> Create a new tourphoto.
	 */
	@RequestMapping(value = "/rest/tourphotos", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void create(@RequestBody TourPhotoDTO tourPhotoDTO) {
		log.debug("REST request to save TourPhotos : {}", tourPhotoDTO);
		tourPhotoService.createPhoto(tourPhotoDTO.getTourId(), tourPhotoDTO.getTourPhotoId(), tourPhotoDTO.getFileName());
	}

	/**
	 * GET /rest/tourphotos -> get all the tourphotos.
	 */
	@RequestMapping(value = "/rest/tourphotos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<TourPhoto> getAll() {
		log.debug("REST request to get all TourPhotos");
		return tourPhotoRepository.findAll();
	}
	
	/**
	 * GET /rest/tourphotos/:id -> Create a new tourphoto.
	 */
	@RequestMapping(value = "/rest/tourphotos/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<TourPhoto>> get(@PathVariable Long id,
			HttpServletResponse response) {
		log.debug("REST request to get TourPhotos : {}", id);
		List<TourPhoto> tourPhoto = tourPhotoRepository.getTourPhotos(id);
		if (tourPhoto == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
		return new ResponseEntity<>(tourPhoto, HttpStatus.OK);
	}
	
	/**
	 * DELETE /rest/tourphotos/:id -> delete the "id" tourphotos.
	 */
	@RequestMapping(value = "/rest/tourphotos/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void delete(@PathVariable Long id) {
		log.debug("REST request to delete TourPhoto : {}", id);
		tourPhotoService.deletePhoto(id);
	}

}
