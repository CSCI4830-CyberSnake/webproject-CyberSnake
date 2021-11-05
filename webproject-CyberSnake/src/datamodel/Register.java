package datamodel;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CREATE TABLE registers 
 * (	username varchar() NOT NULL,
 * 		timeslotId int NOT NULL,
 *      PRIMARY KEY (username, eventId));
 */
@Entity
@Table(name = "registers")
public class Register implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "username")
	private String username;
	
	@Id
	@Column(name = "timeslotId")
	private int timeslotId;

	public Register() {
	}

	public Register(String username, int timeslotId) {
		this.username = username;
		this.timeslotId = timeslotId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTimeslotId() {
		return timeslotId;
	}

	public void setTimeslotId(int timeslotId) {
		this.timeslotId = timeslotId;
	}
	

}