package datamodel;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8 
 * CREATE TABLE timeslots 
 * (	eventId INT NOT NULL,
 * 		date date,
 * 		time varchar(),
 * 		occupancy int,
 *      PRIMARY KEY (eventId, date, time));
 */
@Entity
@Table(name = "timeslots")
public class TimeSlot implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "eventId")
	private Integer eventId;
	
	@Id
	@Column(name = "date")
	private java.sql.Date date;
	
	@Id
	@Column(name = "time")
	private String time;
	
	@Column(name = "occupancy")
	private int occupancy;


	public TimeSlot() {
	}

	public TimeSlot(Integer eventId, Date date, String time, Integer occupancy) {
		this.eventId = eventId;
		this.date = date;
		this.time = time;
		this.occupancy = occupancy;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getOccupancy() {
		return occupancy;
	}

	public void setOccupancy(Integer occupancy) {
		this.occupancy = occupancy;
	}
	
}