package com.kiwianatours.ktbooking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Set;

/**
 * A Tour.
 */
@Entity
@Table(name = "T_TOUR")
public class Tour implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@NotNull
    @Size(min = 0, max = 100)
    @Column(length = 100)
    private String name;
    
	private boolean activated = false;
	
	@Column(name = "short_description", columnDefinition="text")
	private String shortDescription;

	@Column(columnDefinition="text")
	private String description;
	
    @JsonIgnore
    @OneToMany(targetEntity = TourSchedule.class, mappedBy = "tour", cascade = CascadeType.ALL)
    private Set<TourSchedule> tourSchedule;
    
    @JsonIgnore
	@OneToMany(targetEntity = TourPhoto.class, mappedBy = "tour", cascade = CascadeType.ALL)
	private Set<TourPhoto> tourPhoto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<TourSchedule> getTourSchedule() {
		return tourSchedule;
	}

	public void setTourSchedule(Set<TourSchedule> tourSchedule) {
		this.tourSchedule = tourSchedule;
	}

	public Set<TourPhoto> getTourPhoto() {
		return tourPhoto;
	}

	public void setTourPhoto(Set<TourPhoto> tourPhoto) {
		this.tourPhoto = tourPhoto;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tour tour = (Tour) o;

        if (id != tour.id) {
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
