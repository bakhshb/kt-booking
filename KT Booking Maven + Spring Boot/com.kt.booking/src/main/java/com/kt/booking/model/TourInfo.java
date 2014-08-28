package com.kt.booking.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TourInfo")
public class TourInfo {

	private Long id;
	private Tour tour;
	private String name;
	private Date depatureDateTime;
	private Date returnDateTime;
	private double price;
	private String additionalInfo;
	private List<TourPhoto> tourPhoto;
	private List<TourBooking> tourBooking;

	@Id
	@GeneratedValue
	@Column(name = "tour_info_id")
	public Long getId() {
		return id;
	}

	@ManyToOne
	@JoinColumn(name = "tour_id")
	public Tour getTour() {
		return tour;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	@Column(name = "depature_date_time")
	public Date getDepatureDateTime() {
		return depatureDateTime;
	}

	@Column(name = "return_date_time")
	public Date getReturnDateTime() {
		return returnDateTime;
	}

	@Column(name = "price")
	public double getPrice() {
		return price;
	}

	@Column(name = "additional_info", columnDefinition = "Text")
	public String getAdditionalInfo() {
		return additionalInfo;
	}

	@OneToMany(targetEntity = TourPhoto.class, mappedBy = "tourInfo", cascade = CascadeType.ALL)
	public List<TourPhoto> getTourPhoto() {
		return tourPhoto;
	}

	@OneToMany(targetEntity = TourBooking.class, mappedBy = "tourInfo", cascade = CascadeType.ALL)
	public List<TourBooking> getTourBooking() {
		return tourBooking;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDepatureDateTime(Date depatureDateTime) {
		this.depatureDateTime = depatureDateTime;
	}

	public void setReturnDateTime(Date returnDateTime) {
		this.returnDateTime = returnDateTime;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public void setTourPhoto(List<TourPhoto> tourPhoto) {
		this.tourPhoto = tourPhoto;
	}

	public void setTourBooking(List<TourBooking> tourBooking) {
		this.tourBooking = tourBooking;
	}

}
