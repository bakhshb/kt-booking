package com.kt.booking.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table (name="Customer")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7544683772815807914L;

	private Long id ;
	private String firstName;
	@GeneratedValue 
	@Id
	@Column (name="customer_id")
	public Long getId() {
		return id;
	}
	@Column (name="first_name")
	public String getFirstName() {
		return firstName;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
}
