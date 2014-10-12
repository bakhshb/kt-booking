package com.kiwianatours.ktbooking.service;

import javax.inject.Inject;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kiwianatours.ktbooking.domain.Tour;
import com.kiwianatours.ktbooking.domain.TourSchedule;
import com.kiwianatours.ktbooking.repository.TourRepository;
import com.kiwianatours.ktbooking.repository.TourScheduleRepository;

@Service
@Transactional
public class TourScheduleService {

    private final Logger log = LoggerFactory.getLogger(TourScheduleService.class);
    
    @Inject
    private TourScheduleRepository tourScheduleRepository;
    
    @Inject
    private TourRepository tourRepository;
       
    
    public TourSchedule createTourSchedule (Long tourId, Long tourScheduleId, LocalDate depatureDate,
    		LocalDate returnDate, double price, int time, int day){
    	TourSchedule tourSchedule = tourScheduleRepository.findOne(tourScheduleId);
    	Tour tour = tourRepository.findOne(tourId);
    	if (tourSchedule != null){
    		tourSchedule.setTour(tour);
        	tourSchedule.setDepartureDate(depatureDate);
        	tourSchedule.setReturnDate(returnDate);
        	tourSchedule.setPrice(price);
        	tourScheduleRepository.save(tourSchedule);
        	log.debug("Updated Information for TourSchedule: {}", tourSchedule);
    	}else{
    		LocalDate fixedDepature = depatureDate;
    		LocalDate fixedReturn = returnDate;
    		for (int i =0; i < time ; i++){
    			tourSchedule = new TourSchedule();
            	tourSchedule.setTour(tour);
            	tourSchedule.setDepartureDate(fixedDepature);
            	tourSchedule.setReturnDate(fixedReturn);
            	tourSchedule.setPrice(price);
            	tourScheduleRepository.save(tourSchedule);
            	log.debug("Created Information for TourSchedule: {}", tourSchedule);
            	fixedDepature  = new LocalDate (fixedDepature.plusDays(day));
            	fixedReturn = new LocalDate(fixedReturn.plusDays(day));
    		}
    	}
    	return tourSchedule;
    }

}
