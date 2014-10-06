package com.kiwianatours.ktbooking.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kiwianatours.ktbooking.domain.Tour;
import com.kiwianatours.ktbooking.domain.TourPhoto;
import com.kiwianatours.ktbooking.repository.TourPhotoRepository;
import com.kiwianatours.ktbooking.repository.TourRepository;

@Service
@Transactional
public class TourPhotoService {

    private final Logger log = LoggerFactory.getLogger(TourPhotoService.class);
    
    @Inject
	private TourPhotoRepository tourPhotoRepository;

	@Inject
	private TourRepository tourRepository;
    
    public void uploadPhotos (MultipartFile file){
    	if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("C:/hello/"
								+ file.getOriginalFilename())));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {

			}
		}
    }
    /*
     * primary photo
     */
    
    public void createPhoto (Long tourId, Long tourPhotoId, String fileName){
    	Tour tour = tourRepository.findOne(tourId);
		if (tour != null) {
			List<TourPhoto> tourPhotos = tourPhotoRepository.findByTour(tour);
			if (tourPhotoId != null) {
				if (tourPhotos != null && tourPhotos.size() != 0) {
					for (TourPhoto tourP : tourPhotos) {
						if (tourP.getId().equals(tourPhotoId)) {
							tourP.setActivated(true);
						} else {
							tourP.setActivated(false);
						}
					}
					tourPhotoRepository.save(tourPhotos);
					log.debug("Updated Information for TourPhoto: {}",tourPhotos);
				}
			} else {
				TourPhoto tourPhoto = new TourPhoto();
				tourPhoto.setTour(tour);
				tourPhoto.setPhoto(fileName);
				tourPhotoRepository.save(tourPhoto);
				log.debug("Created Information for TourPhoto: {}", tourPhoto);
			}
		}
    }
    
    public void deletePhoto (Long id){
    	TourPhoto tourPhoto = tourPhotoRepository.findOne(id);
		if (tourPhoto != null){
			try{
				File location = new File("C:/hello/"+ tourPhoto.getPhoto());
				location.delete();
				tourPhotoRepository.delete(id);
				log.debug("Deleted Information for TourPhoto: {}", tourPhoto);
			}catch(Exception e){

			}
		}
    }

}