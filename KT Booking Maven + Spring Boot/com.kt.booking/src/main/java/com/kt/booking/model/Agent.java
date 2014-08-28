package com.kt.booking.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Agent")
public class Agent {

	private Long id;
	private Account account;
	private String name;
	private String address;
	private String email;
	private String contactNo;
	private List<Booking> booking;

	@Id
	@GeneratedValue
	@Column(name = "agent_id")
	public Long getId() {
		return id;
	}

	@OneToOne(targetEntity = Account.class, mappedBy = "agent", cascade = CascadeType.ALL)
	public Account getAccount() {
		return account;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	@Column(name = "contact_no")
	public String getContactNo() {
		return contactNo;
	}

	@OneToMany(targetEntity = Booking.class, mappedBy = "agent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Booking> getBooking() {
		return booking;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}
}
