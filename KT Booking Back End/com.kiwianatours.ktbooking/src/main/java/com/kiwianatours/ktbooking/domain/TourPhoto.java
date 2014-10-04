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
import javax.validation.constraints.Size;

/**
 * A TourPhoto.
 */
@Entity
@Table(name = "T_TOUR_PHOTO")
public class TourPhoto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Size(min = 1, max = 100)
	@Column(length = 100)
	private String photo;
	
	private boolean activated = false;
	
	@ManyToOne
	@JoinColumn(name = "tour_id")
	private Tour tour;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TourPhoto tourPhoto = (TourPhoto) o;

        if (id != tourPhoto.id) {
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
        return "Tourinfo{" +
                "id=" + id +
                ", photo='" + photo + '\'' +
                ", activated='" + activated + '\'' +
                '}';
    }
	

}
