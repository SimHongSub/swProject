package com.marketplace.filesystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	 * filePath - String variable to save c source file path.
	 * fileStream - FileInputStream to read file.
	 */
	//private String filePath = null;
	//private FileInputStream fileStream = null;
	
	/*public FileProcessing(String filePath) {
		this.filePath = filePath;
		try {
			this.fileStream = new FileInputStream(this.filePath);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}*/
	
	public FileProcessing() {
		
	}
	
	/**
	 * Return fileContents after read c source file and save the content.
	 * 
	 * @return fileContents
	 * @throws IOException 
	 * @exception FileInputStream Exception.
	 */
	public String readFile(String filePath) {
		String fileContents = "";
		FileInputStream fileStream = null;
		
		try {
			fileStream = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		try {
			byte[] readBuffer = new byte[fileStream.available()];
			
			while(fileStream.read(readBuffer) != -1) {
				fileContents += new String(readBuffer);
			}
			
			fileStream.close();
			
			return fileContents;
		}catch(Exception e) {
			e.getStackTrace();
			
			return e.getMessage();
		}
	}
	
	/**
	 * The method to write user info to file.
	 * 
	 * @param list - User info list
	 * @param filePath - Output file path
	 * @exception IOException.
	 */
	public void writeUserFile(ArrayList<User> list, String filePath) {
		File outFile = new File(filePath);
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
			
			for(int i=0;i<list.size();i++) {
				User user = list.get(i);
				
				bw.write(user.getId() + " : " + user.getPassword() + " : " + user.getName() + " : " + user.getPhoneNumber() + " : " + user.getEmail() + "\n");
			}
			
			bw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The method to write book info to file.
	 * 
	 * @param list - Book info list
	 * @param filePath - Output file path
	 * @exception IOException.
	 */
	public void writeBookFile(ArrayList<Book> list, String filePath) {
		File outFile = new File(filePath);
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
			
			for(int i=0;i<list.size();i++) {
				Book book = list.get(i);
				
				bw.write(book.getIsbn() + " : " + book.getTitle() + " : " + book.getAuthor() + " : " + book.getPublisher() + " : " + book.getDate() + " : " + book.getPrice() + " : " + book.getState() + "\n");
			}
			
			bw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
