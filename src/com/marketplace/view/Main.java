package com.marketplace.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.marketplace.controller.AdminController;
import com.marketplace.controller.GeneralController;
import com.marketplace.controller.MainController;
import com.marketplace.main.MarketPlace;
import com.marketplace.model.Book;
import com.marketplace.view.table.MainTableCell;

public class Main {
	//메인 페이지 frame
	private JFrame frm;
	
	public Main() {
		frm = new JFrame("헌책 장터 시스템 - 메인 페이지");
	}

	public void show(ArrayList<Book> list) {
		// table 초기화
		String header[] = {"ISBN", "제목", "저자", "출판사", "출판년도", "상태", "가격", "판매자", ""};
		DefaultTableModel model = new DefaultTableModel(header, 0);
		
		for(int i=0; i<list.size(); i++) {
			Book book = list.get(i);
			String info[] = {book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getDate(), book.getState(), book.getPrice(), book.getUserId()};
			
			model.addRow(info);
		}
		
		JTable table = new JTable(model);
		
		table.getColumnModel().getColumn(8).setCellRenderer(new MainTableCell(table, frm));
		table.getColumnModel().getColumn(8).setCellEditor(new MainTableCell(table, frm));
		
		table.setRowHeight(30);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		//frame 사이즈, 화면상 위치 설정
		frm.setSize(1000, 700);
		frm.setLocationRelativeTo(null);
				
		//frame layout설정
		frm.setLayout(new BorderLayout());
		
		//panel 초기화
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(1,4));

		String items[] = {"ISBN번호", "제목", "저자", "판매자ID"};
		JComboBox<String> searchBox = new JComboBox<String>(items);
		
		JTextField searchText = new JTextField();
		
		northPanel.add(searchBox);
		northPanel.add(searchText);
		
		JButton searchBtn = new JButton("검색");
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController mainController = new MainController();
				
				if(!searchText.getText().equals("")) {
					frm.dispose();
					mainController.search(searchBox.getSelectedItem().toString(), searchText.getText());	
				}else {
					frm.dispose();
					mainController.showView();
				}
			}
		});
		
		northPanel.add(searchBtn);
		
		if(MarketPlace.my.getAuthority().equals("ADMIN")) {
			JButton adminBtn = new JButton("관리자 페이지");
			adminBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					AdminController adminController = new AdminController();
					
					frm.dispose();
					adminController.showView();
				}
			});
			
			northPanel.add(adminBtn);
		}else {
			JButton myBtn = new JButton("My Book");
			myBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					GeneralController generalController = new GeneralController();
					
					frm.dispose();
					generalController.showView();
				}
			});
			northPanel.add(myBtn);
		}
		
		frm.add(northPanel, BorderLayout.NORTH);
		frm.add(scrollPane);
		
		frm.setVisible(true);
		
		//x박스 클릭 action
		frm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
}
