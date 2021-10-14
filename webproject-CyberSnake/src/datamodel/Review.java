package datamodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8 
 * CREATE TABLE reviews 
 * (	reviewId INT NOT NULL AUTO_INCREMENT,
 * 		username varchar(),
 * 		stars int,
 * 		comment varchar(),
 *      PRIMARY KEY (eventId));
 */
@Entity
@Table(name = "reviews")
public class Review implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reviewId")
	private Integer reviewId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "stars")
	private int stars;
	
	@Column(name = "comment")
	private String comment;


	public Review() {
	}

	public Review(Integer reviewId, String username, int stars, String comment) {
		this.reviewId = reviewId;
		this.username = username;
		this.stars = stars;
		this.comment = comment;
	}
	
	public Review(String username, int stars, String comment) {
		this.username = username;
		this.stars = stars;
		this.comment = comment;
	}

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	
}