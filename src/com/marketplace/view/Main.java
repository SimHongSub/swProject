package com.marketplace.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.marketplace.controller.AdminController;
import com.marketplace.controller.MainController;
import com.marketplace.filesystem.FileProcessing;
import com.marketplace.main.MarketPlace;
import com.marketplace.model.Book;

public class Main {
	private JFrame frm;
	
	public Main() {
		frm = new JFrame("헌책 장터 시스템");
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
		
		table.getColumnModel().getColumn(8).setCellRenderer(new TableCell(table));
		table.getColumnModel().getColumn(8).setCellEditor(new TableCell(table));
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		//frame 사이즈, 화면상 위치 설정
		frm.setSize(1000, 700);
		frm.setLocationRelativeTo(null);
				
		//frame layout설정
		frm.setLayout(new BorderLayout());
		

		JPanel northPanel = new JPanel();
		//northPanel.setLayout(mgr);
		
		JButton searchBtn = new JButton("검색");
		
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
			northPanel.add(myBtn);
		}
		
		frm.add(northPanel, BorderLayout.NORTH);
		frm.add(scrollPane);
		
		frm.setVisible(true);
	}
	
	@SuppressWarnings("serial")
	class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
        JButton purchaseBtn;
 
        public TableCell(JTable table) {
        	purchaseBtn = new JButton("구입");
        	
        	purchaseBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					HashMap<String, String> result = new HashMap<String, String>();
					MainController mainController = new MainController();
					
					result = mainController.purchase(table.getSelectedRow());
					
					JOptionPane.showMessageDialog(frm, result.get("message"), "Message", JOptionPane.INFORMATION_MESSAGE, null);
				}
			});
        }
 
        @Override
        public Object getCellEditorValue() {
            return null;
        }
 
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {

            return purchaseBtn;
        }
 
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {

            return purchaseBtn;
        }
    }
}
