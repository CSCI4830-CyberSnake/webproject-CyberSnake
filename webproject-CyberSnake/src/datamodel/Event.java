package datamodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CREATE TABLE events 
 * (	eventId INT NOT NULL AUTO_INCREMENT,
 * 		username varchar(),
 * 		name varchar(),
 * 		description varchar(),
 *      PRIMARY KEY (eventId));
 */
@Entity
@Table(name = "events")
public class Event implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eventId")
	private Integer eventId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;


	public Event() {
	}

	public Event(Integer eventId, String username, String name, String description) {
		this.eventId = eventId;
		this.username = username;
		this.name = name;
		this.description = description;
	}
	
	public Event(String username, String name, String description) {
		this.username = username;
		this.name = name;
		this.description = description;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}