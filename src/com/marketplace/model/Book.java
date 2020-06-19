package com.marketplace.model;

/** 
 * Model class responsible for save the book information.
 * 
 * @date 2020.06.12
 * @author SimHongSub
 * @version 1.0
 */
public class Book {
	/**
	 * isbn - 책 ISBN 넘버
	 * title - 책 제목
	 * author - 저자
	 * publisher - 출판사
	 * date - 출판년도
	 * state - 상태(Excellent. Good, Fair)
	 * price - 가격
	 * userId - 주인 id
	 */
	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private String date;
	private String state;
	private String price;
	private String userId;
	
	public Book(String isbn, String title, String author, String publisher, String date, String state, String price, String userId) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.date = date;
		this.state = state;
		this.price = price;
		this.userId = userId;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
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
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
