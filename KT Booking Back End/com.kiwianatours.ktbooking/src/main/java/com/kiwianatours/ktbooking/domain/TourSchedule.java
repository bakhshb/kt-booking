package com.kiwianatours.ktbooking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import com.kiwianatours.ktbooking.domain.util.CustomLocalDateSerializer;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.joda.time.LocalDate;
import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * A Tour Schedule.
 */
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "T_TOUR_SCHEDULE")
@Audited
public class TourSchedule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3371021503546680236L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@ManyToOne
	private Tour tour;
	
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	@Column(name = "depature_date", nullable = false)
	private LocalDate departureDate;

	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	@Column(name = "return_date", nullable = false)
	private LocalDate returnDate;

	private double price;
	
	private int attending;
	
	@JsonIgnore
	@OneToMany(targetEntity = TourBooking.class, mappedBy = "tourSchedule", cascade = CascadeType.ALL)
	private Set<TourBooking> tourBooking;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAttending() {
		return attending;
	}

	public void setAttending(int attending) {
		this.attending = this.attending + attending;
	}

	public Set<TourBooking> getTourBooking() {
		return tourBooking;
	}

	public void setTourBooking(Set<TourBooking> tourBooking) {
		this.tourBooking = tourBooking;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TourSchedule tourSchedule = (TourSchedule) o;

        if (id != tourSchedule.id) {
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
        return "TourSchedule{" +
                "id=" + id +
                ", departureDate=" + departureDate +
                ", returnDate=" + returnDate +
                ", price=" + price +
                ", attending=" + attending +
                '}';
    }
}
