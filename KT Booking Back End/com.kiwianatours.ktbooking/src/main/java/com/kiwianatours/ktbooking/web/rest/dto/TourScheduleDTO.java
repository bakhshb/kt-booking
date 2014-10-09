package com.kiwianatours.ktbooking.web.rest.dto;

import org.joda.time.LocalDate;
import com.kiwianatours.ktbooking.domain.Tour;

public class TourScheduleDTO {

	private Long id = 0L;

	private Tour tour;

	private LocalDate departureDate;

	private LocalDate returnDate;

	private double price;
	
	private int attending;
	
	private int time = 1;
	
	private int day;
	
	public TourScheduleDTO() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAttending() {
		return attending;
	}

	public void setAttending(int attending) {
		this.attending = attending;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

}
