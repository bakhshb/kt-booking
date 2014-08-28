package com.kt.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.booking.model.Customer;
import com.kt.booking.repository.CustomerRepository;
import com.kt.booking.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	@Transactional
	public List<Customer> findAll() {
		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	@Transactional
	public Customer findById(Long id) {
		return (Customer) customerRepository.findOne(id);
	}

	@Override
	@Transactional
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
		
	}

}
