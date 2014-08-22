package com.kt.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kt.booking.model.Customer;
import com.kt.booking.service.CustomerService;

@RestController
@RequestMapping("/rest/customer")
public class CustomerCtrl {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@Secured("ROLE_ADMIN")
	public List<Customer> findAll() {
		return (List<Customer>) customerService.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Customer> findById(@PathVariable Long id) {

		return new ResponseEntity<Customer>(customerService.findById(id),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Customer> save(@RequestBody final Customer customer) {
		return new ResponseEntity<Customer>(customerService.save(customer), HttpStatus.OK);
	}

}
