package com.kt.booking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kt.booking.model.Agent;

@Repository
public interface AgentRepository extends CrudRepository<Agent, Long>{

}