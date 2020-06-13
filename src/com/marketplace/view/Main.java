package com.marketplace.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.marketplace.filesystem.FileProcessing;
import com.marketplace.model.Book;

public class Main {
	private JFrame frm;
	
	public Main() {
		frm = new JFrame("헌책 장터 시스템");
	}

	public void show(ArrayList<Book> list) {
		JPanel pn = new JPanel();
		/*BorderLayout bordelLayout = new BorderLayout();
		
		pn.setLayout(bordelLayout);*/
		
		//frame 사이즈, 화면상 위치 설정
		frm.setSize(1000, 700);
		frm.setLocationRelativeTo(null);
				
		//frame layout설정
		//frm.setLayout(new GridLayout(3, 2));
		
		String header[] = {"ISBN", "제목", "저자", "출판사", "출판년도", "상태", "가격", "판매자"};
		String[][] contents = new String[list.size()][8];
		
		for(int i=0; i<list.size(); i++) {
			Book book = list.get(i);
			
			contents[i][0] = book.getIsbn();
			contents[i][1] = book.getTitle();
			contents[i][2] = book.getAuthor();
			contents[i][3] = book.getPublisher();
			contents[i][4] = book.getDate();
			contents[i][5] = book.getState();
			contents[i][6] = book.getPrice();
			contents[i][7] = book.getUserId();
		}
		
		JTable table = new JTable(contents, header);
		JScrollPane scrollPane = new JScrollPane(table);
		
		frm.add(scrollPane);
				
		//component 생성
		JButton signUpBtn = new JButton("회원가입");
		
		frm.setVisible(true);
	}
}
