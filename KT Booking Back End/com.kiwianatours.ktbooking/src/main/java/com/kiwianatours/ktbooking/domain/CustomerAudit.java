package com.kiwianatours.ktbooking.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.joda.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import com.kiwianatours.ktbooking.domain.util.CustomLocalDateSerializer;

/**
 * A Customer.
 */
@Entity
@Table(name = "T_CUSTOMER_AUD")
public class CustomerAudit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4955483111996589598L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column(name = "REVTYPE")
	private byte revType;

    @Column(name = "first_name")
	private String firstName;

    @Column(name = "last_name")
	private String lastName;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	@Column(name = "birthday", nullable = false)
	private LocalDate birthday;

	@Column(name = "permission_from")
	private String permissionFrom;

	private String gender;

	private String nationality;

	@Email
	@Column(length = 100)
	private String email;

	@Column(name = "contact_no")
	private String contactNo;

	@Column(name = "additional_info", columnDefinition = "Text")
	private String additionalinfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte getRevType() {
		return revType;
	}

	public void setRevType(byte revType) {
		this.revType = revType;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		CustomerAudit customerAUD = (CustomerAudit) o;

		if (id != customerAUD.id) {
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
		return "Customer{" + "id=" + id + ", firstName='" + firstName + '\''
				+ ", lastName=" + lastName + '\'' + ", birthday=" + birthday
				+ '\'' + ", permissionFrom=" + permissionFrom + '\''
				+ ", gender=" + gender + '\'' + ", nationality=" + nationality
				+ '\'' + ", email=" + email + '\'' + ", contactNo=" + contactNo
				+ '\'' + ", additionalinfo=" + additionalinfo + '\'' + '}';
	}

}
