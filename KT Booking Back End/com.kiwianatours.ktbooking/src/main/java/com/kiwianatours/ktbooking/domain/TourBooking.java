package com.kiwianatours.ktbooking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.joda.time.DateTime;
import org.springframework.boot.actuate.audit.listener.AuditListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Tour Booking.
 */
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "T_TOUR_BOOKING")
@Audited
public class TourBooking {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@ManyToOne
	private Booking booking;

	@ManyToOne
	@JoinColumn(name = "tour_schedule_id")
	private TourSchedule tourSchedule;

	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "booking_date")
	private DateTime bookingDate = DateTime.now();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public TourSchedule getTourSchedule() {
		return tourSchedule;
	}

	public void setTourSchedule(TourSchedule tourSchedule) {
		this.tourSchedule = tourSchedule;
	}

	public DateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(DateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		TourBooking tourBooking = (TourBooking) o;

		if (id != tourBooking.id) {
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
		return "TourBooking{" + "id=" + id + '}';
	}
}
