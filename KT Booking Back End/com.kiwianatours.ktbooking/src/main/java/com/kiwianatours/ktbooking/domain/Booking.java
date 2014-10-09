package com.kiwianatours.ktbooking.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.springframework.boot.actuate.audit.listener.AuditListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Booking.
 */
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "T_BOOKING")
@Audited
public class Booking extends AbstractAuditingEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@ManyToOne
	private Customer customer;
		
	@Size(min = 1, max = 50)
	@Column(name = "payment_method", length = 50)
	private String paymentMethod;
	
	private double amount;
	
	private int status = 1;
	
	@JsonIgnore
	@OneToMany(targetEntity = TourBooking.class, mappedBy = "booking", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<TourBooking> tourBooking;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<TourBooking> getTourBooking() {
		return tourBooking;
	}

	public void setTourBooking(Set<TourBooking> tourBooking) {
		this.tourBooking = tourBooking;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Booking booking = (Booking) o;

		if (id != booking.id) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

	@Override
	public String toString() {
		return "Booking{" + "id=" + id +
				", paymentMethod='"+ paymentMethod +'\'' 	+
				", amount="+ amount +'\''+
				", status="+ status +'\''+
				'}';
	}
}
