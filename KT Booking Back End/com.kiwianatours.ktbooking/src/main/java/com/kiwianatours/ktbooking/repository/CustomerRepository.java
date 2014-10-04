package com.kiwianatours.ktbooking.repository;

import com.kiwianatours.ktbooking.domain.Customer;
        import org.springframework.data.jpa.repository.JpaRepository;
		import java.lang.String;

/**
 * Spring Data JPA repository for the Customer entity.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findByEmail(String email);

}
