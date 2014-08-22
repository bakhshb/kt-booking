package com.kt.booking.service;

import java.util.List;

import com.kt.booking.model.Customer;

public interface CustomerService {

	public List<Customer> findAll();
	
	public Customer findById(Long id);
	
	public Customer save(Customer customer);
}
