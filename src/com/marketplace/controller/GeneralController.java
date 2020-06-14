package com.marketplace.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.marketplace.filesystem.FileProcessing;
import com.marketplace.main.MarketPlace;
import com.marketplace.model.Book;
import com.marketplace.util.ISBNUtil;
import com.marketplace.view.General;

public class GeneralController {
	
	private General view;
	private ArrayList<Book> bookList;
	ArrayList<Book> myBooks;
	FileProcessing fp;
	
	public GeneralController() {
		view = new General();
		bookList = new ArrayList<Book>();
		myBooks = new ArrayList<Book>();
		fp = new FileProcessing();
		
		fp.readBookFile("bookInfo", bookList);
		
		for(int i=0; i<bookList.size(); i++) {
			Book book = bookList.get(i);
			
			if(book.getUserId().equals(MarketPlace.my.getId())) {
				myBooks.add(book);
			}
		}
	}
	
	public void showView() {
		view.show(myBooks);
	}
	
	public void search(String searchItem, String searchText) {
		ArrayList<Book> searchBooks = new ArrayList<Book>();
		
		if(searchItem.equals("ISBN번호")){
			for(int i=0; i<bookList.size(); i++) {
				Book book = bookList.get(i);
				
				if(book.getIsbn().equals(searchText)) {
					searchBooks.add(book);
					
					break;
				}
			}
		}else if(searchItem.equals("제목")) {
			for(int i=0; i<bookList.size(); i++) {
				Book book = bookList.get(i);
				
				if(book.getTitle().contains(searchText)) {
					searchBooks.add(book);
				}
			}
		}else {
			for(int i=0; i<bookList.size(); i++) {
				Book book = bookList.get(i);
				
				if(book.getAuthor().equals(searchText)) {
					searchBooks.add(book);
				}
			}
		}
		
		view.show(searchBooks);	
	}
	
	public HashMap<String, String> register(String title, String author, String publisher, String date, String state, String price){
		HashMap<String, String> result = new HashMap<String, String>();
		
		if(title.equals("")) {
			result.put("message", "제목을 입력하셔야합니다.");
		}else {
			ISBNUtil isbnUtil = new ISBNUtil();
			
			Book book = new Book(isbnUtil.create(), title, author, publisher, date, state, price, MarketPlace.my.getId());
			
			myBooks.add(book);
			bookList.add(book);
			
			fp.writeBookFile(bookList, "bookInfo");
			
			result.put("message", "success");
		}
		
		return result;
	}
	
	public Book select(int index) {
		return myBooks.get(index);
	}
	
	public HashMap<String, String> modify(int index, String title, String author, String publisher, String date, String state, String price) {
		HashMap<String, String> result = new HashMap<String, String>();
		
		if(title.equals("")) {
			result.put("message", "제목을 입력하셔야합니다.");
		}else {
			myBooks.get(index).setTitle(title);
			myBooks.get(index).setAuthor(author);
			myBooks.get(index).setPublisher(publisher);
			myBooks.get(index).setDate(date);
			myBooks.get(index).setState(state);
			myBooks.get(index).setPrice(price);
			
			for(int i=0; i<bookList.size(); i++) {
				Book book = bookList.get(i);
				
				if(myBooks.get(index).getIsbn().equals(book.getIsbn())) {
					bookList.get(i).setTitle(title);
					bookList.get(i).setAuthor(author);
					bookList.get(i).setPublisher(publisher);
					bookList.get(i).setDate(date);
					bookList.get(i).setState(state);
					bookList.get(i).setPrice(price);
					
					break;
				}
			}
			
			fp.writeBookFile(bookList, "bookInfo");
			
			result.put("message", "success");
		}
		
		return result;
	}
	
	public void delete(int index) {
		Book deletedBook = myBooks.get(index);
	
		for(int i=0; i< bookList.size(); i++) {
			Book book = bookList.get(i);
			
			if(deletedBook.getIsbn().equals(book.getIsbn())) {
				bookList.remove(i);
				
				break;
			}
		}
		
		myBooks.remove(index);
		
		fp.writeBookFile(bookList, "bookInfo");
	}
}
