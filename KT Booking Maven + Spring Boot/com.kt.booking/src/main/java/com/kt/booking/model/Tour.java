package com.kt.booking.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Tour")
public class Tour {

	private Long id;
	private String name;
	private String description;
	private String shortDescription;
	private List<TourInfo> tourInfo;

	@Id
	@GeneratedValue
	@Column(name = "tour_id")
	public Long getId() {
		return id;
	}

	@Column(name = "Name")
	public String getName() {
		return name;
	}

	@Column(name = "description", columnDefinition = "Text")
	public String getDescription() {
		return description;
	}

	@Column(name = "short_description", columnDefinition = "Text")
	public String getShortDescription() {
		return shortDescription;
	}

	@OneToMany(targetEntity = TourInfo.class, mappedBy = "tour", cascade = CascadeType.ALL)
	public List<TourInfo> getTourInfo() {
		return tourInfo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public void setTourInfo(List<TourInfo> tourInfo) {
		this.tourInfo = tourInfo;
	}

}
