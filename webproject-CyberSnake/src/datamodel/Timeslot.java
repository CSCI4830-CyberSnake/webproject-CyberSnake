package datamodel;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * CREATE TABLE timeslots 
 * (	timeslotId INT NOT NULL
 * 		eventId INT,
 * 		date date,
 * 		startTime varchar(),
 * 		endTime varchar(),
 * 		occupancy int,
 *      PRIMARY KEY (timeslotId));
 */
@Entity
@Table(name = "timeslots")
public class Timeslot implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "timeslotId")
	private Integer timeslotId;
	
	@Column(name = "eventId")
	private Integer eventId;
	
	@Column(name = "date")
	private java.sql.Date date;
	
	@Column(name = "startTime")
	private String startTime;
	
	@Column(name = "endTime")
	private String endTime;
	
	@Column(name = "occupancy")
	private int occupancy;


	public Timeslot() {
	}

	public Timeslot(Integer eventId, Date date, String startTime, String endTime, Integer occupancy) {
		this.eventId = eventId;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.occupancy = occupancy;
	}

	public Integer getTimeslotId() {
		return timeslotId;
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

	public String getStartTime() {
		return startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getOccupancy() {
		return occupancy;
	}

	public void setOccupancy(Integer occupancy) {
		this.occupancy = occupancy;
	}
	
}