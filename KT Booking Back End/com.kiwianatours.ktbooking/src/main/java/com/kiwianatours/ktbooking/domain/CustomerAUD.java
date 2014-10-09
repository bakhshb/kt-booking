package com.kiwianatours.ktbooking.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class CustomerAUD implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	
	@ManyToOne
	@JoinColumn(name="REV")
	private Revinfo revinfo;

	@Column(name = "REVTYPE")
	private int revType;

	@Size(min = 1, max = 50)
    @Column(name = "first_name", length = 50)
	private String firstName;

	@Size(min = 1, max = 50)
    @Column(name = "last_name", length = 50)
	private String lastName;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	@Column(name = "birthday", nullable = false)
	private LocalDate birthday;

	@Size(min = 1, max = 100)
	@Column(name = "permission_from", length = 100)
	private String permissionFrom;

	private String gender;

	private String nationality;

	@Email
	@Size(min = 1, max = 100)
	@Column(length = 100)
	private String email;

	@Size(min = 1, max = 11)
	@Column(name = "contact_no", length = 11)
	private String contactNo;

	@Column(name = "additional_info", columnDefinition = "Text")
	private String additionalinfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Revinfo getRevinfo() {
		return revinfo;
	}

	public void setRevinfo(Revinfo revinfo) {
		this.revinfo = revinfo;
	}

	public int getRevType() {
		return revType;
	}

	public void setRevType(int revType) {
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

		CustomerAUD customerAUD = (CustomerAUD) o;

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
