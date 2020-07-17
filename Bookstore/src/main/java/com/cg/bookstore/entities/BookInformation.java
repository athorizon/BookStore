package com.cg.bookstore.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="bookstore_book")
@DynamicInsert
@DynamicUpdate
public class BookInformation {

	@Id
	@Column(name="book_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bookIdGenerator")
	@SequenceGenerator(name="bookIdGenerator", initialValue=1000)
	private int bookId;
	
	@Column(name="title")
	@Size(min=10, max=128)
	private String title;
	 
	@Column(name="author")
	@Size(min=5, max= 64)
	private String author;
	
	@Column(name="description")
	@Size(min=200, max=2000)
	private String description;
	
	@Column(name="isbn_number")
	@Size(min=10, max=15)
	private String isbnNumber;
	
	@Column(name="publish_date")
	private LocalDate publishDate;
	
	@Column(name="last_update_time")
	private LocalDate lastUpdateTime;
	
	@Column(name="copies_sold")
	private int copies_sold;
	
	@Column(name="price")
	private float price;

	@ManyToOne
	@JoinColumn(name="category_id", referencedColumnName = "category_id")
	private BookCategory category = new BookCategory();
	
	public BookCategory getCategory() {
		return category;
	}

	public void setCategory(BookCategory category) {
		this.category = category;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbnNumber() {
		return isbnNumber;
	}

	public void setIsbnNumber(String isbnNumber) {
		this.isbnNumber = isbnNumber;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public LocalDate getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(LocalDate lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getCopies_sold() {
		return copies_sold;
	}

	public void setCopies_sold(int copies_sold) {
		this.copies_sold = copies_sold;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}


	
}