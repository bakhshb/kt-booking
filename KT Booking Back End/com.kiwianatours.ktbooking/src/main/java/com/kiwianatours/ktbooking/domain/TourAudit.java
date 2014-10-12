package com.kiwianatours.ktbooking.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A Tour.
 */
@Entity
@Table(name = "T_TOUR_AUD")
public class TourAudit implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7118240741843360185L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column(name = "REVTYPE")
	private byte revType;
	
    private String name;
    
	private boolean activated = false;
	
	@Column(name = "short_description" , columnDefinition="text")
	private String shortDescription;

	@Column(columnDefinition="text")
	private String description;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TourAudit tourAudit = (TourAudit) o;

        if (id != tourAudit.id) {
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
        return "Tour{" +
                ", name='" + name + '\'' +        
                ", activated='" + activated + '\'' +
                ", shortDescription=" + shortDescription +
                ", description=" + description +
                '}';
    }
}