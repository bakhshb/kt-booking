package com.kt.booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kt.booking.model.Account;
import java.lang.String;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

	Account findByUserName(String username);

}
