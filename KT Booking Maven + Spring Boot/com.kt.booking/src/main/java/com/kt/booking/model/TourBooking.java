package com.kt.booking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TourBooking")
public class TourBooking {

	private Long id;
	private Booking booking;
	private TourInfo tourInfo;
	private String bookingDate;
	private String additionalInfo;

	@Id
	@GeneratedValue
	@Column(name = "tour_booking_id")
	public Long getId() {
		return id;
	}

	@ManyToOne
	@JoinColumn(name = "booking_id")
	public Booking getBooking() {
		return booking;
	}

	@ManyToOne
	@JoinColumn(name = "tour_info_id")
	public TourInfo getTourInfo() {
		return tourInfo;
	}

	@Column(name = "booking_date")
	public String getBookingDate() {
		return bookingDate;
	}

	@Column(name = "additional_info", columnDefinition = "Text")
	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public void setTourInfo(TourInfo tourInfo) {
		this.tourInfo = tourInfo;
	}

}
