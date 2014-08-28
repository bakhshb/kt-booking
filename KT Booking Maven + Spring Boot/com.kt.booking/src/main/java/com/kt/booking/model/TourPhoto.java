package com.kt.booking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TourPhoto")
public class TourPhoto {

	private Long id;
	private String photo;
	private TourInfo tourInfo;

	@Id
	@GeneratedValue
	@Column(name = "tour_photo_id")
	public Long getId() {
		return id;
	}

	@Column(name = "photo")
	public String getPhoto() {
		return photo;
	}

	@ManyToOne
	@JoinColumn(name = "tour_info_id")
	public TourInfo getTourInfo() {
		return tourInfo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setTourInfo(TourInfo tourInfo) {
		this.tourInfo = tourInfo;
	}

}
