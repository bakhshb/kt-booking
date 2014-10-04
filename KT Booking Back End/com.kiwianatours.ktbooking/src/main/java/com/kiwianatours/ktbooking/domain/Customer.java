package com.kiwianatours.ktbooking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import com.kiwianatours.ktbooking.domain.util.CustomLocalDateSerializer;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

/**
 * A Customer.
 */
@Entity
@Table(name = "T_CUSTOMER")
public class Customer extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Size(min = 1, max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;
    
    @Size(min = 1, max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;
    
    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;
    
    @Size(min = 1, max = 100)
    @Column(name="permission_from", length = 100)
    private String permissionFrom;
    
    @Size(min = 1, max = 10)
    @Column(length = 10)
    private String gender;
       
    @Size(min = 1, max = 100)
    @Column(length = 100)
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
	
	@JsonIgnore
	@OneToMany(targetEntity = Booking.class, mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Booking> booking;   

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

	public List<Booking> getBooking() {
		return booking;
	}

	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Customer customer = (Customer) o;

        if (id != customer.id) {
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
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName=" + lastName + '\''+
                ", birthday=" + birthday + '\''+
                ", permissionFrom=" + permissionFrom + '\''+
                ", gender=" + gender + '\''+
                ", nationality=" + nationality + '\''+
                ", email=" + email + '\''+
                 ", contactNo=" + contactNo + '\''+
                ", additionalinfo=" + additionalinfo + '\''+
                '}';
    }
}
