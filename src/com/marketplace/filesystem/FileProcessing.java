package com.marketplace.filesystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.marketplace.model.Book;
import com.marketplace.model.User;

/** 
 * Class responsible for file I/O.
 * 
 * @date 2020.06.12
 * @author SimHongSub
 * @version 1.0
 */
public class FileProcessing {
	/**
	 * Save user information after read userInfo file.
	 
	 * @param filePath - Input file path
	 * @param list - User object list
	 * @throws IOException 
	 * @exception FileInputStream Exception.
	 */
	public void readUserFile(String filePath, ArrayList<User> list) {
		File file = new File(filePath);
		
		if(file.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = null;
				
				try {
					while((line = br.readLine()) != null && !line.equals("")) {
						String[] pieces = line.split(":");
						
						User user = new User(pieces[0], pieces[1], pieces[2], pieces[3], pieces[4], pieces[5], pieces[6]);
						
						list.add(user);
					}
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Save book information after read bookInfo file.
	 
	 * @param filePath - Input file path
	 * @param list - Book object list
	 * @throws IOException 
	 * @exception FileInputStream Exception.
	 */
	public void readBookFile(String filePath, ArrayList<Book> list) {
		File file = new File(filePath);
		
		if(file.exists()) {
			try {
				BufferedReader inFile = new BufferedReader(new FileReader(file));
				String sLine = null;
				
				try {
					while((sLine = inFile.readLine()) != null) {
						String[] pieces = sLine.split(":");
						
						Book book = new Book(pieces[0], pieces[1], pieces[2], pieces[3], pieces[4], pieces[5], pieces[6], pieces[7]);
						
						list.add(book);
					}
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * The method to write user info to file.
	 * 
	 * @param list - User object list
	 * @param filePath - Output file path
	 * @exception IOException.
	 */
	public void writeUserFile(ArrayList<User> list, String filePath) {
		File outFile = new File(filePath);
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
			
			for(int i=0;i<list.size();i++) {
				User user = list.get(i);
				
				bw.write(user.getId() + ":" + user.getPassword() + ":" + user.getName() + ":" + user.getPhoneNumber() + ":" + user.getEmail() + ":" + user.getAuthority() + ":" + user.getState() + "\n");
			}
			
			bw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The method to write book info to file.
	 * 
	 * @param list - Book object list
	 * @param filePath - Output file path
	 * @exception IOException.
	 */
	public void writeBookFile(ArrayList<Book> list, String filePath) {
		File outFile = new File(filePath);
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
			
			for(int i=0;i<list.size();i++) {
				Book book = list.get(i);
				
				bw.write(book.getIsbn() + ":" + book.getTitle() + ":" + book.getAuthor() + ":" + book.getPublisher() + ":" + book.getDate() + ":" + book.getState() + ":" + book.getPrice() + ":" + book.getUserId() + "\n");
			}
			
			bw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
