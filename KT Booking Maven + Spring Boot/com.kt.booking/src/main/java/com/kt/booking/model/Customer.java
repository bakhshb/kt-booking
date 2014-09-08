package com.kt.booking.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Customer")
public class Customer {

	private long id;
	private String firstName;
	private String lastName;
	private String nickName;
	private String nationality;
	private Date birthDate;
	private String gender;
	private String permissionFrom;
	private String address;
	private String email;
	private String contactNo;
	private String additionalInfo;
	private Date created;
	private String createdBy;
	private List<Booking> booking;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
	public long getId() {
		return id;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	@Column(name = "nick_name")
	public String getNickName() {
		return nickName;
	}

	@Column(name = "nationality")
	public String getNationality() {
		return nationality;
	}

	@Column(name = "birth_date")
	public Date getBirthDate() {
		return birthDate;
	}

	@Column(name = "gender")
	public String getGender() {
		return gender;
	}

	@Column(name = "permission_from")
	public String getPermissionFrom() {
		return permissionFrom;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	@Column(name = "contact_no")
	public String getContactNo() {
		return contactNo;
	}

	@Column(name = "additional_info", columnDefinition = "Text")
	public String getAdditionalInfo() {
		return additionalInfo;
	}

	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreated() {
		return created;
	}

	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	@OneToMany(targetEntity = Booking.class, mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Booking> getBooking() {
		return booking;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setPermissionFrom(String permissionFrom) {
		this.permissionFrom = permissionFrom;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}

}