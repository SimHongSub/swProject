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
import com.marketplace.controller.MainController;
import com.marketplace.exception.AdminException;
import com.marketplace.exception.MainException;
import com.marketplace.model.User;
import com.marketplace.view.table.AdminTableCell;

/** 
 * Class responsible for admin page frontend view.
 * 
 * @date 2020.06.12
 * @author SimHongSub
 * @version 1.0
 */
public class Admin {
	private JFrame frm;
	
	public Admin() {
		frm = new JFrame("헌책 장터 시스템 - 관리자 페이지");
	}

	public void show(ArrayList<User> list) {
		String header[] = {"ID", "이름", "전화번호", "이메일", "상태", "", ""};
		DefaultTableModel model = new DefaultTableModel(header, 0);
		
		for(int i=0; i<list.size(); i++) {
			User user = list.get(i);
			String info[] = {user.getId(), user.getName(), user.getPhoneNumber(), user.getEmail(), user.getState()};
			
			model.addRow(info);
		}
		
		JTable table = new JTable(model);

		table.getColumnModel().getColumn(5).setCellRenderer(new AdminTableCell(table, frm, 5));
		table.getColumnModel().getColumn(5).setCellEditor(new AdminTableCell(table, frm, 5));
		
		table.getColumnModel().getColumn(6).setCellRenderer(new AdminTableCell(table, frm, 6));
		table.getColumnModel().getColumn(6).setCellEditor(new AdminTableCell(table, frm, 6));
		
		table.setRowHeight(30);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		frm.setSize(1000, 700);
		frm.setLocationRelativeTo(null);
				
		frm.setLayout(new BorderLayout());

		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(1,4));

		String items[] = {"사용자ID", "이름"};
		JComboBox<String> searchBox = new JComboBox<String>(items);
		
		JTextField searchText = new JTextField();
		
		northPanel.add(searchBox);
		northPanel.add(searchText);
		
		JButton searchBtn = new JButton("검색");
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminController adminController = new AdminController();
				
				if(!searchText.getText().equals("")) {
					frm.dispose();
					try {
						adminController.search(searchBox.getSelectedItem().toString(), searchText.getText());
					} catch (AdminException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}else {
					frm.dispose();
					try {
						adminController.showView();
					} catch (AdminException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		northPanel.add(searchBtn);
		
		JButton mainBtn = new JButton("메인 페이지");
		mainBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController mainController = new MainController();
				
				frm.dispose();
				try {
					mainController.showView();
				} catch (MainException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		northPanel.add(searchBtn);
		northPanel.add(mainBtn);
		
		frm.add(northPanel, BorderLayout.NORTH);
		frm.add(scrollPane);
		
		frm.setVisible(true);
		
		frm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
}
