package com.kt.booking.service;

import java.util.List;

import com.kt.booking.model.Customer;

public interface CustomerService {

	/**
	 * this method return all the customer from database
	 * 
	 * @return List<Customer>
	 */
	public List<Customer> findAll();

	/**
	 * this method look for customer by id from database
	 * 
	 * @param id
	 * @return Customer
	 */
	public Customer findById(Long id);

	/**
	 * this method insert customer into database
	 * 
	 * @param customer
	 * @return
	 */
	public Customer save(Customer customer);
}
