package com.kt.booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kt.booking.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
}
