package com.kiwianatours.ktbooking.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kiwianatours.ktbooking.domain.TourBooking;
import com.kiwianatours.ktbooking.domain.TourSchedule;
import com.kiwianatours.ktbooking.repository.CustomerRepository;
import com.kiwianatours.ktbooking.repository.TourBookingRepository;
import com.kiwianatours.ktbooking.repository.TourScheduleRepository;

@Service
@Transactional
public class CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerService.class);
    
    @Inject
    private CustomerRepository customerRepository;
    
    @Inject
    private TourScheduleRepository tourScheduleRepository;
    
    @Inject
    private TourBookingRepository tourBookingRepository;
    
    
    public void deleteCustomer (Long id){
    	List<TourBooking> tourBookings = tourBookingRepository.findCustomerBookingByCustomer(id);
    	if (tourBookings.size() !=0){
    		List<TourSchedule> tourSchedules = new ArrayList<TourSchedule>();
        	for (TourBooking tourBooking : tourBookings){
        		TourSchedule tourSchedule = tourBooking.getTourSchedule(); 
        		tourSchedules.add(tourSchedule);
        	}
        	
        	for (TourSchedule tourSchedule : tourSchedules){
        		tourSchedule.setAttending(-1);
        		tourScheduleRepository.save(tourSchedule);
        		log.debug("Updated Information for tour Schedule {}", tourSchedule);
        	}
        	customerRepository.delete(id);
    	}

    		customerRepository.delete(id);
    	
    }

}
