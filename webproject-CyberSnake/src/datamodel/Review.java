package datamodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CREATE TABLE reviews 
 * (	username varchar(),
 * 		eventId int,
 * 		stars int,
 * 		comment varchar(),
 * 		anon boolean
 *      PRIMARY KEY (username, eventId));
 */
@Entity
@Table(name = "reviews")
public class Review implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "username")
	private String username;
	
	@Id
	@Column(name = "eventId")
	private int eventId;
	
	@Column(name = "stars")
	private int stars;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "anon")
	private boolean anon;


	public Review() {
	}

	public Review(String username, int eventId, int stars, String comment, boolean anon) {
		this.username = username;
		this.eventId = eventId;
		this.stars = stars;
		this.comment = comment;
		this.anon = anon;
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

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public boolean getAnon() {
		return anon;
	}

	public void setAnon(boolean anon) {
		this.anon = anon;
	}
	
}