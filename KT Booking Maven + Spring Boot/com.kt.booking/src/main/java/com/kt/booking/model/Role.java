package com.kt.booking.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Role")
public class Role {

	private Long id;
	private Integer role;
	private List<Account> account;

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

	@OneToMany(targetEntity = Role.class, mappedBy = "role", cascade = CascadeType.ALL)
	public List<Account> getAccount() {
		return account;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}

}