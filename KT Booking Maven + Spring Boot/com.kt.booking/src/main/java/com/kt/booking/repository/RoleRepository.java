package com.kt.booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kt.booking.model.Role;

import java.lang.Integer;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

	Role findByRole(Integer role);

}
