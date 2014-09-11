package com.kt.booking.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "Agent")
public class Agent {

	private Long id;
	private Account account;
	private String name;
	private String address;
	private String email;
	private String contactNo;
	private Date created;
	private String createdBy;
	private List<Booking> booking;

	@Id
	@GeneratedValue
	@Column(name = "agent_id")
	public Long getId() {
		return id;
	}

	@OneToOne(targetEntity = Account.class, mappedBy = "agent")
	public Account getAccount() {
		return account;
	}
	@Audited
	@Column(name = "name")
	public String getName() {
		return name;
	}
	@Audited
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	@Audited
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	@Audited
	@Column(name = "contact_no")
	public String getContactNo() {
		return contactNo;
	}
	@Audited
	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreated() {
		return created;
	}
	@Audited
	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
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

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}
}
