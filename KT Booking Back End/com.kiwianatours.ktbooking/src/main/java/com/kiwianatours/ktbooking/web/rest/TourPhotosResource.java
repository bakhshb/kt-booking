package com.kiwianatours.ktbooking.web.rest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
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
import com.kiwianatours.ktbooking.config.Constants;
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
	
	@Inject
    private Environment env;
	
	/**
	 * POST /rest/tourphotos -> Upload a new upload photos.
	 */
	@RequestMapping(value = "/rest/tourphotos", method = RequestMethod.POST)
	@Timed
	public ResponseEntity<TourPhoto> upload(@RequestBody MultipartFile file) {
		log.debug("REST request to save photo : {}", file);
		HttpHeaders responseHeader = new HttpHeaders();
		if (!file.isEmpty()) {
			try {
				LocalTime time = new LocalTime();
				LocalDate  date = new LocalDate();
				int parseTime = time.getHourOfDay() +time.getMillisOfSecond() ;
				String parseDate =date.toString();
				String parseDateNTime = parseTime +"_"+ parseDate;
				byte[] bytes = file.getBytes();
				
				
				String finalPath = null;
				if (env.getActiveProfiles().equals(Constants.SPRING_PROFILE_PRODUCTION)){
					finalPath = System.getenv("OPENSHIFT_DATA_DIR");
				}else{
					// files
					File currentDirFile = new File("");
					String absolutePath = currentDirFile.getAbsolutePath();	
					File newDirFile = new File(absolutePath);
					finalPath = newDirFile.getParent();
				}
				boolean success = new File(finalPath+ "/upload").mkdir();
				boolean exist = new File (finalPath +"/upload").exists();
				if (success || exist){
					String fileType = file.getContentType().toString().replace("/", ".");
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(new File(finalPath +"/upload/" +parseDateNTime+fileType )));
					stream.write(bytes);
					stream.close();
					responseHeader.set("filename",parseDateNTime+fileType);
					log.debug(parseDateNTime+fileType + " was uploaded to", finalPath +"/upload/");
				}
			} catch (Exception e) {
				log.error("Upload exception", e);
			}
		}
		return new ResponseEntity<>(responseHeader,HttpStatus.OK);
	}
	
	/**
	 * PUT /rest/tourphotos -> Create a new tourphoto.
	 * get the name of image and insert it in the database
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
		List<TourPhoto> tourPhoto = tourPhotoRepository.findTourPhotosByTourId(id);
		if (tourPhoto.size() > 0){
			return new ResponseEntity<>(tourPhoto, HttpStatus.OK);
		}	
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
