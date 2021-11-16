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
 * 		time varchar(),
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
	
	@Column(name = "time")
	private String time;
	
	@Column(name = "occupancy")
	private int occupancy;


	public Timeslot() {
	}

	public Timeslot(Integer eventId, Date date, String time, Integer occupancy) {
		this.eventId = eventId;
		this.date = date;
		this.time = time;
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

	public String getTime() {
		return time;
	}
	
	public String getFormattedTime() {
		String result = "";
		String[] times = time.split("\\s*-\\s*");
		String[] beginTime = times[0].split("\\s*:\\s*");
		String[] endTime = times[1].split("\\s*:\\s*");
		if(Integer.parseInt(beginTime[0]) < 12) {
			if( Integer.parseInt(beginTime[0]) == 0)
				result += "12:" + beginTime[1] + "am";
			else
				result += beginTime[0] + ":" + beginTime[1] + "am";
			
		}
		else {
			int hour = Integer.parseInt(endTime[0]) - 12;
			result += String.valueOf(hour) + ":" + endTime[1] + "pm";
		}
		return result;
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