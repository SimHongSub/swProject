package com.marketplace.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.marketplace.controller.GeneralController;
import com.marketplace.controller.MainController;
import com.marketplace.model.Book;
import com.marketplace.view.table.GeneralTableCell;

public class General {
	private JFrame frm;
	
	public General() {
		frm = new JFrame("헌책 장터 시스템 - 사용자 페이지");
	}
	
	public void show(ArrayList<Book> list) {
		// table 초기화
		String header[] = {"ISBN", "제목", "저자", "출판사", "출판년도", "상태", "가격", "", ""};
		DefaultTableModel model = new DefaultTableModel(header, 0);
				
		for(int i=0; i<list.size(); i++) {
			Book book = list.get(i);
			String info[] = {book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getDate(), book.getState(), book.getPrice()};
					
			model.addRow(info);
		}
				
		JTable table = new JTable(model);
				
		table.getColumnModel().getColumn(7).setCellRenderer(new GeneralTableCell(table, frm, 7));
		table.getColumnModel().getColumn(7).setCellEditor(new GeneralTableCell(table, frm, 7));

		table.getColumnModel().getColumn(8).setCellRenderer(new GeneralTableCell(table, frm, 8));
		table.getColumnModel().getColumn(8).setCellEditor(new GeneralTableCell(table, frm, 8));
				
		table.setRowHeight(30);
				
		JScrollPane scrollPane = new JScrollPane(table);
				
		//frame 사이즈, 화면상 위치 설정
		frm.setSize(1000, 700);
		frm.setLocationRelativeTo(null);
						
		//frame layout설정
		frm.setLayout(new BorderLayout());
				

		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(1,4));

		String items[] = {"ISBN번호", "제목", "저자"};
		JComboBox<String> searchBox = new JComboBox<String>(items);
		
		JTextField searchText = new JTextField();
		
		northPanel.add(searchBox);
		northPanel.add(searchText);
		
		JButton searchBtn = new JButton("검색");
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneralController generalController = new GeneralController();
				
				if(!searchText.getText().equals("")) {
					frm.dispose();
					generalController.search(searchBox.getSelectedItem().toString(), searchText.getText());	
				}else {
					frm.dispose();
					generalController.showView();
				}
			}
		});
		
		northPanel.add(searchBtn);
				
		JButton registerBtn = new JButton("등록");
		registerBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame registerFrm = new JFrame("책 등록");
				
				//frame 사이즈, 화면상 위치 설정
				registerFrm.setSize(250, 200);
				registerFrm.setLocationRelativeTo(null);
				
				//frame layout설정
				registerFrm.setLayout(new GridLayout(7, 2));
				
				JLabel titleLabel = new JLabel("제목", SwingConstants.RIGHT);
				JTextField titleText = new JTextField();
				
				JLabel authorLabel = new JLabel("저자", SwingConstants.RIGHT);
				JTextField authorText = new JTextField();
				
				JLabel publisherLabel = new JLabel("출판사", SwingConstants.RIGHT);
				JTextField publisherText = new JTextField();
				
				JLabel dateLabel = new JLabel("출판년도", SwingConstants.RIGHT);
				JTextField dateText = new JTextField();
				
				JLabel stateLabel = new JLabel("상태", SwingConstants.RIGHT);
				String states[] = {"Excellent", "Good", "Fail"};
				JComboBox<String> stateBox = new JComboBox<String>(states);
				
				JLabel priceLabel = new JLabel("가격", SwingConstants.RIGHT);
				JTextField priceText = new JTextField();
				
				JButton confirmBtn = new JButton("확인");
				confirmBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						HashMap<String, String> result = new HashMap<String, String>();
						GeneralController generalController = new GeneralController();
						
						result = generalController.register(titleText.getText(), authorText.getText(), publisherText.getText(), dateText.getText(), stateBox.getSelectedItem().toString(), priceText.getText());
					
						if(result.get("message").equals("success")) {
							JOptionPane.showMessageDialog(frm, "등록되었습니다.", "Message", JOptionPane.INFORMATION_MESSAGE, null);
							
							registerFrm.dispose();
							frm.dispose();
							generalController.showView();
						}else {
							JOptionPane.showMessageDialog(registerFrm, result.get("message"), "Message", JOptionPane.INFORMATION_MESSAGE, null);
						}
					}
				});
				
				JButton cancleBtn = new JButton("취소");
				cancleBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						registerFrm.dispose();
					}
				});
				
				registerFrm.add(titleLabel);
				registerFrm.add(titleText);
				
				registerFrm.add(authorLabel);
				registerFrm.add(authorText);
				
				registerFrm.add(publisherLabel);
				registerFrm.add(publisherText);
				
				registerFrm.add(dateLabel);
				registerFrm.add(dateText);
				
				registerFrm.add(stateLabel);
				registerFrm.add(stateBox);
				
				registerFrm.add(priceLabel);
				registerFrm.add(priceText);
				
				registerFrm.add(confirmBtn);
				registerFrm.add(cancleBtn);
				
				registerFrm.setVisible(true);
				
				//x박스 클릭 action
				registerFrm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});
				
		JButton mainBtn = new JButton("메인 페이지");
		mainBtn.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController mainController = new MainController();
						
				frm.dispose();
				mainController.showView();
			}
		});
				 
		northPanel.add(searchBtn);
		northPanel.add(registerBtn);
		northPanel.add(mainBtn);
				
		frm.add(northPanel, BorderLayout.NORTH);
		frm.add(scrollPane);
				
		frm.setVisible(true);
				
		//x박스 클릭 action
		frm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
}
