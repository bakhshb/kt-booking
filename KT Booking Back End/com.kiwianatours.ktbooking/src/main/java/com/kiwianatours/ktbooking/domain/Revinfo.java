package com.kiwianatours.ktbooking.domain;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A REVINFO.
 */
@Entity
@Table(name = "REVINFO")
public class Revinfo {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long REV;
	
	private Long REVTSTMP;
	
	@JsonIgnore
	@OneToMany(targetEntity = CustomerAUD.class, mappedBy = "revinfo", fetch = FetchType.LAZY)
	private List<CustomerAUD> customerAUD;

	public Long getREV() {
		return REV;
	}

	public void setREV(Long rEV) {
		REV = rEV;
	}

	public Long getREVTSTMP() {
		return REVTSTMP;
	}

	public void setREVTSTMP(Long rEVTSTMP) {
		REVTSTMP = rEVTSTMP;
	}
	
	
}
