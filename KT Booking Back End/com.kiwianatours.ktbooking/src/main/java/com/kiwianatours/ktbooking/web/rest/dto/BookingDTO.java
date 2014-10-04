package com.kiwianatours.ktbooking.web.rest.dto;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import com.kiwianatours.ktbooking.domain.Booking;
import com.kiwianatours.ktbooking.domain.TourSchedule;

public class BookingDTO {

    private Long id;

    private String firstName;
    
    private String lastName;
    
    private LocalDate birthday;
    
    private String permissionFrom;
    
    private String gender;
       
    private String nationality;
    
    private String email;
    
	private String contactNo;

    private String additionalinfo;
    
    private String createdBy;
 
    private DateTime createdDate;

    private String lastModifiedBy;

    private DateTime lastModifiedDate;

	private Booking booking;

	private TourSchedule tourSchedule;
	
	private int status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getPermissionFrom() {
		return permissionFrom;
	}

	public void setPermissionFrom(String permissionFrom) {
		this.permissionFrom = permissionFrom;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAdditionalinfo() {
		return additionalinfo;
	}

	public void setAdditionalinfo(String additionalinfo) {
		this.additionalinfo = additionalinfo;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public DateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public DateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
