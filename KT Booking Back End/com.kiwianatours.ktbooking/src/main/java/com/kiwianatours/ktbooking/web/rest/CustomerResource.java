package com.kiwianatours.ktbooking.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kiwianatours.ktbooking.domain.Customer;
import com.kiwianatours.ktbooking.repository.BookingRepository;
import com.kiwianatours.ktbooking.repository.CustomerRepository;
import com.kiwianatours.ktbooking.security.AuthoritiesConstants;
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
 * REST controller for managing Customer.
 */
@RestController
@RequestMapping("/app")
public class CustomerResource {

	private final Logger log = LoggerFactory.getLogger(CustomerResource.class);

	@Inject
	private CustomerRepository customerRepository;
	
	@Inject
	private BookingRepository bookingRepository;

	/**
	 * POST  /rest/customers -> Create a new customer.
	 */
	@RequestMapping(value = "/rest/customers",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public void create(@RequestBody Customer customer) {
		log.debug("REST request to save Customer : {}", customer);
		customerRepository.save(customer);
	}

	/**
	 * GET  /rest/customers -> get all the customers.
	 */
	@RequestMapping(value = "/rest/customers",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Secured({AuthoritiesConstants.ADMIN,AuthoritiesConstants.USER})
	@Timed
	public List<Customer> getAll() {
		log.debug("REST request to get all Customers");
		return customerRepository.findAll();
	}

	/**
	 * GET  /rest/customers/:id -> get the "id" customer.
	 */
	@RequestMapping(value = "/rest/customers/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Secured({AuthoritiesConstants.ADMIN,AuthoritiesConstants.USER})
	@Timed
	public ResponseEntity<Customer> get(@PathVariable Long id, HttpServletResponse response) {
		log.debug("REST request to get Customer : {}", id);
		Customer customer = customerRepository.findOne(id);
		if (customer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	/**
	 * DELETE  /rest/customers/:id -> delete the "id" customer.
	 */
	@RequestMapping(value = "/rest/customers/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Secured(AuthoritiesConstants.ADMIN)
	@Timed
	public ResponseEntity<Customer> delete(@PathVariable Long id) {
		log.debug("REST request to delete Customer : {}", id);
		Customer customer = customerRepository.findOne(id);
		
		if(bookingRepository.findByCustomer(customer).size() == 0){
			customerRepository.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

}
