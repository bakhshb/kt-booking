package com.kt.booking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Role")
public class Role {

	private Long id;
	private Integer role;
	
	@Id
	@GeneratedValue
	@Column(name = "role_id")
	public Long getId() {
		return id;
	}

	@Column(name = "role")
	public Integer getRole() {
		return role;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
}