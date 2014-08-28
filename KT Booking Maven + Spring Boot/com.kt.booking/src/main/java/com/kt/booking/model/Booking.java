package com.kt.booking.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Booking")
public class Booking {

	private Long id;
	private Customer customer;
	private Agent agent;
	private String paidMethod;
	private double amount;
	private List<TourBooking> tourBooking;

	@Id
	@GeneratedValue
	@Column(name = "booking_id")
	public Long getId() {
		return id;
	}

	@ManyToOne
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return customer;
	}

	@Column(name = "paid_method")
	public String getPaidMethod() {
		return paidMethod;
	}

	@Column(name = "amount")
	public double getAmount() {
		return amount;
	}

	@OneToMany(targetEntity = TourBooking.class, mappedBy = "booking", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<TourBooking> getTourBooking() {
		return tourBooking;
	}

	@ManyToOne
	@JoinColumn(name = "agent_id")
	public Agent getAgent() {
		return agent;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public void setPaidMethod(String paidMethod) {
		this.paidMethod = paidMethod;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setTourBooking(List<TourBooking> tourBooking) {
		this.tourBooking = tourBooking;
	}

}
