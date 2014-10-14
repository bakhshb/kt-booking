package com.kiwianatours.ktbooking.web.rest.dto;

public class TourPhotoDTO {
	
	private String fileName;
	
	private Long tourId;
	
	private Long tourPhotoId;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getTourId() {
		return tourId;
	}

	public void setTourId(Long tourId) {
		this.tourId = tourId;
	}

	public Long getTourPhotoId() {
		return tourPhotoId;
	}

	public void setTourPhotoId(Long tourPhotoId) {
		this.tourPhotoId = tourPhotoId;
	}

}
