package com.kiwianatours.ktbooking.web.rest;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.codahale.metrics.annotation.Timed;
import com.kiwianatours.ktbooking.domain.TourBooking;
import com.kiwianatours.ktbooking.repository.TourBookingRepository;
import com.kiwianatours.ktbooking.security.AuthoritiesConstants;
import com.kiwianatours.ktbooking.service.BookingService;
import com.kiwianatours.ktbooking.web.rest.dto.BookingDTO;

/**
 * REST controller for managing Customer.
 */
@RestController
@RequestMapping("/app")
public class BookingResource {

	private final Logger log = LoggerFactory.getLogger(BookingResource.class);
    
    @Inject
    private TourBookingRepository tourBookingRepository;
    
    @Inject
    private BookingService bookingService;
          
    
    /**
     * POST  /rest/bookings -> Create a new customer booking.
     */
    @RequestMapping(value = "/rest/bookings",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody BookingDTO bookingDTO, HttpServletRequest request,
			  HttpServletResponse response) {
        log.debug("REST request to save Customer booking: {}", bookingDTO);
		bookingService.createCustomerBooking(bookingDTO.getTourSchedule().getId(),bookingDTO.getBooking().getPaymentMethod(), bookingDTO.getId(),
				bookingDTO.getFirstName(), bookingDTO.getLastName(),bookingDTO.getBirthday(), bookingDTO.getPermissionFrom(),bookingDTO.getGender(),
				bookingDTO.getNationality(),bookingDTO.getEmail(), bookingDTO.getContactNo(),bookingDTO.getAdditionalinfo(), request, response);
    }
    
    /**
     * GET  /rest/bookings -> get all the bookings.
     */
    @RequestMapping(value = "/rest/bookings",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<TourBooking> getAll() {
        log.debug("REST request to get all booking");
        return tourBookingRepository.findAll();
    }


    /**
     * GET  /rest/bookings/:id -> get the "id" booking.
     */
    @RequestMapping(value = "/rest/bookings/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<TourBooking>> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get booking : {}", id);
        List<TourBooking> tourBooking = bookingService.getCustomerBooking(id);
        if (tourBooking == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tourBooking, HttpStatus.OK);
    }
    
    /**
     * PUT  /rest/bookings/:id -> put the "id" booking.
     */
    @RequestMapping(value = "/rest/bookings",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void put(@RequestBody BookingDTO bookingDTO) {
        log.debug("REST request to delete booking : {}", bookingDTO);
        bookingService.updateBookingStatus(bookingDTO.getId(), bookingDTO.getStatus());
    }

    /**
     * DELETE  /rest/bookings/:id -> delete the "id" booking.
     */
    @RequestMapping(value = "/rest/bookings/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured(AuthoritiesConstants.ADMIN)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete booking : {}", id);
        bookingService.deleteBooking(id);
    }
    
    /**
     * GET  /rest/tour/bookings/:id -> get the "id" booking.
     */
    @RequestMapping(value = "/rest/tour/bookings/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<TourBooking>> getTourBooking(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get booking : {}", id);
        List<TourBooking> tourBooking = bookingService.getTourBooking(id);
        if (tourBooking == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tourBooking, HttpStatus.OK);
    }
    
}
