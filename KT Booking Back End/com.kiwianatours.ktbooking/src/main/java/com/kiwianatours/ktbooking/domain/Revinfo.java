package com.kiwianatours.ktbooking.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
