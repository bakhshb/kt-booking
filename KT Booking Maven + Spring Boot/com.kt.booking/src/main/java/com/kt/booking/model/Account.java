package com.kt.booking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "Account")
public class Account {

	private Long id;
	private Agent agent;
	private Role role;
	private String username;
	private String password;
	private boolean isEnabled;

	@Id
	@GeneratedValue
	@Column(name = "account_id")
	public Long getId() {
		return id;
	}

	@OneToOne
	@JoinColumn(name = "agent_id")
	public Agent getAgent() {
		return agent;
	}

	@ManyToOne
	@JoinColumn(name = "role_id")
	public Role getRole() {
		return role;
	}

	@Column(unique = true, name = "user_name")
	public String getUsername() {
		return username;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	@Column(name = "is_enabled")
	public boolean isEnabled() {
		return isEnabled;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

}
