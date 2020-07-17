package com.cg.bookstore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="bookstore_customer_review")
@DynamicInsert
@DynamicUpdate
public class CustomerReview {
	
	@Id
	@Column(name="review_id")
	private int ratingId;
	
	@Column(name="book_rating")
	@Min(1)
	@Max(2)
	private int bookRating;
	
	@Column(name="review_headling")
	@Size(min=20,max=128)
	private String reviewHeadling;
	
	@Column(name="review_comment",length=300)
	@Size(min=100,max=300)
	private String reviewComment;

	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

	public int getBookRating() {
		return bookRating;
	}

	public void setBookRating(int bookRating) {
		this.bookRating = bookRating;
	}

	public String getReviewHeadling() {
		return reviewHeadling;
	}

	public void setReviewHeadling(String reviewHeadling) {
		this.reviewHeadling = reviewHeadling;
	}

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}
	
	

}
