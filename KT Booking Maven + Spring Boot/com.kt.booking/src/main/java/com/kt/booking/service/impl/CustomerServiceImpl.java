package com.kt.booking.service.impl;

import java.util.Date;
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
		customer.setCreated(new Date());
		return customerRepository.save(customer);
		
	}

	@Override
	@Transactional
	public Customer update(Customer customer) {
		Customer updateCustomer = customerRepository.findOne(customer.getId());
		updateCustomer.setFirstName(customer.getFirstName());
		updateCustomer.setLastName(customer.getLastName());
		updateCustomer.setNickName(customer.getNickName());
		updateCustomer.setNationality(customer.getNationality());
		updateCustomer.setBirthDate(customer.getBirthDate());
		updateCustomer.setAddress(customer.getAddress());
		updateCustomer.setGender(customer.getGender());
		updateCustomer.setEmail(customer.getEmail());
		updateCustomer.setContactNo(customer.getContactNo());
		updateCustomer.setCreated(new Date());
		updateCustomer.setCreatedBy(customer.getCreatedBy());
		return customerRepository.save(updateCustomer);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		 customerRepository.delete(id);
	}

}
