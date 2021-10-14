package datamodel;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8 
 * CREATE TABLE registers 
 * (	username varchar() NOT NULL,
 * 		eventId int NOT NULL,
 * 		date date NOT NULL,
 * 		time varchar() NOT NULL,
 *      PRIMARY KEY (username, eventId, date, time));
 */
@Entity
@Table(name = "registers")
public class Register implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "username")
	private String username;
	
	@Id
	@Column(name = "eventId")
	private int eventId;
	
	@Id
	@Column(name = "date")
	private java.sql.Date date;
	
	@Id
	@Column(name = "time")
	private String time;


	public Register() {
	}

	public Register(String username, int eventId, Date date, String time) {
		this.username = username;
		this.eventId = eventId;
		this.date = date;
		this.time = time;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
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
	

}