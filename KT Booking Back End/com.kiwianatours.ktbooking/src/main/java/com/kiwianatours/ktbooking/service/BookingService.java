package com.kiwianatours.ktbooking.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kiwianatours.ktbooking.domain.Booking;
import com.kiwianatours.ktbooking.domain.Customer;
import com.kiwianatours.ktbooking.domain.TourBooking;
import com.kiwianatours.ktbooking.domain.TourSchedule;
import com.kiwianatours.ktbooking.repository.BookingRepository;
import com.kiwianatours.ktbooking.repository.CustomerRepository;
import com.kiwianatours.ktbooking.repository.TourBookingRepository;
import com.kiwianatours.ktbooking.repository.TourScheduleRepository;

@Service
@Transactional
public class BookingService {

	private final Logger log = LoggerFactory.getLogger(BookingService.class);

	@Inject
	private CustomerRepository customerRepository;

	@Inject
	private BookingRepository bookingRepository;

	@Inject
	private TourBookingRepository tourBookingRepository;

	@Inject
	private TourScheduleRepository tourScheduleRepository;

	public Customer createCustomerBooking (Long tourScheduleId, String paymentMethod, Long id, String firstName, String lastName, LocalDate birthday,
			String permissionFrom, String gender,String nationality,String email,String contactNo,String additionalinfo){

		TourSchedule tourSchedule = tourScheduleRepository.findOne(tourScheduleId);
		Customer customer = customerRepository.findByEmail(email);
		if (customer != null){
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setBirthday(birthday);
			customer.setPermissionFrom(permissionFrom);
			customer.setGender(gender);
			customer.setNationality(nationality);
			customer.setContactNo(contactNo);
			customer.setAdditionalinfo(additionalinfo);
			customerRepository.save(customer);
			log.debug("Update Information for Customer  {}", customer);
			Booking booking = new Booking();
			booking.setCustomer(customer);
			booking.setPaymentMethod(paymentMethod);
			booking.setAmount(tourSchedule.getPrice());
			bookingRepository.save(booking);
			log.debug("Created Information for  Booking {}", booking);
			TourBooking tourBooking = new TourBooking();
			tourBooking.setBooking(booking);
			tourSchedule.setAvailability(1);
			tourBooking.setTourSchedule(tourSchedule);
			tourBookingRepository.save(tourBooking);
			log.debug("Created Information for tour booking {}", tourBooking);

		}else{
			customer = new Customer ();
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setBirthday(birthday);
			customer.setPermissionFrom(permissionFrom);
			customer.setGender(gender);
			customer.setNationality(nationality);
			customer.setEmail(email);
			customer.setContactNo(contactNo);
			customer.setAdditionalinfo(additionalinfo);
			customerRepository.save(customer);
			log.debug("Created Information for Customer  {}", customer);
			Booking booking = new Booking();
			booking.setCustomer(customer);
			booking.setPaymentMethod(paymentMethod);
			booking.setAmount(tourSchedule.getPrice());
			bookingRepository.save(booking);
			log.debug("Created Information for  Booking {}", booking);
			TourBooking tourBooking = new TourBooking();
			tourBooking.setBooking(booking);
			tourSchedule.setAvailability(1);
			tourBooking.setTourSchedule(tourSchedule);
			tourBookingRepository.save(tourBooking);
			log.debug("Created Information for tour booking {}", tourBooking);

		}
		return customer;
	}
	/*
	 * return the customer booking
	 */
	public List <TourBooking> getCustomerBooking(Long id) {
		List <TourBooking> tourBookings  = new ArrayList<TourBooking>();
		tourBookings = tourBookingRepository.findCustomerBookingByCustomer(id);
		return tourBookings;
	}
	/*
	 * change the booking status
	 */
	public void updateBookingStatus (Long id, int status){
		Booking booking = bookingRepository.findOne(id);
		if (booking != null){
			TourBooking tourBooking= tourBookingRepository.findTourBookingByBooking(id);
			TourSchedule tourSchedule = tourBooking.getTourSchedule();
			System.out.println(status);
			switch(status){
			case 1: 
				System.out.println(status);
				// approved
				if (booking.getStatus() == 1){
					System.out.println(status);
					tourScheduleRepository.save(tourSchedule);
					log.debug("Updated Information for tour Schedule {}", tourSchedule);
					bookingRepository.save(booking);
					log.debug("Updated Information for Booking {}", booking);
				}else{
					booking.setStatus(status);
					tourSchedule.setAvailability(1);
					tourScheduleRepository.save(tourSchedule);
					log.debug("Updated Information for tour Schedule {}", tourSchedule);
					bookingRepository.save(booking);
					log.debug("Updated Information for Booking {}", booking);
				}         	
				break;
			case 2:
				System.out.println(status);
				// pending 
				if (booking.getStatus() == 2 || booking.getStatus() == 3 || booking.getStatus() == 4){
					booking.setStatus(status);
					tourScheduleRepository.save(tourSchedule);
					log.debug("Updated Information for tour Schedule {}", tourSchedule);
					bookingRepository.save(booking);
				}else{
					booking.setStatus(status);
					tourSchedule.setAvailability(-1);
					tourScheduleRepository.save(tourSchedule);
					log.debug("Updated Information for tour Schedule {}", tourSchedule);
					bookingRepository.save(booking);
					log.debug("Updated Information for Booking {}", booking);
				}
				break;
			case 3:
				// refund
				if (booking.getStatus() == 2 || booking.getStatus() == 3 || booking.getStatus() == 4){
					booking.setStatus(status);
					tourScheduleRepository.save(tourSchedule);
					log.debug("Updated Information for tour Schedule {}", tourSchedule);
					bookingRepository.save(booking);
					log.debug("Updated Information for Booking {}", booking);
				}else{
					booking.setStatus(status);
					tourSchedule.setAvailability(-1);
					tourScheduleRepository.save(tourSchedule);
					log.debug("Updated Information for tour Schedule {}", tourSchedule);
					bookingRepository.save(booking);
					log.debug("Updated Information for Booking {}", booking);
				}
				break;
			case 4: 
				// cancel
				if (booking.getStatus() == 2 || booking.getStatus() == 3 || booking.getStatus() == 4){
					booking.setStatus(status);
					tourScheduleRepository.save(tourSchedule);
					log.debug("Updated Information for tour Schedule {}", tourSchedule);
					bookingRepository.save(booking);
					log.debug("Updated Information for Booking {}", booking);
				}else{
					booking.setStatus(status);
					tourSchedule.setAvailability(-1);
					tourScheduleRepository.save(tourSchedule);
					log.debug("Updated Information for tour Schedule {}", tourSchedule);
					bookingRepository.save(booking);
					log.debug("Updated Information for Booking {}", booking);
				}
				break;            	
			}

		}
	}

	/*
	 * Delete Booking
	 */

	public void deleteBooking (Long id){
		TourBooking tourBooking= tourBookingRepository.findTourBookingByBooking(id);
		TourSchedule tourSchedule = tourBooking.getTourSchedule();
		tourSchedule.setAvailability(-1);
		tourScheduleRepository.save(tourSchedule);
		log.debug("Updated Information for tour Schedule {}", tourSchedule);
		bookingRepository.delete(id);
	}
	/*
	 * return all the booking related to the tour schedule id
	 */
	public List <TourBooking> getTourBooking(Long id) {
		List <TourBooking> tourBookings  = new ArrayList<TourBooking>();
		tourBookings = tourBookingRepository.findApprovedBookingByTourSchedule(id);
		return tourBookings;
	}

}
