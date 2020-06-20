package com.marketplace.view.table;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.marketplace.controller.GeneralController;
import com.marketplace.exception.GeneralException;
import com.marketplace.model.Book;

/** 
 * Class that creates a button in genetal user page book list table.
 * 
 * @date 2020.06.12
 * @author SimHongSub
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GeneralTableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer  {
	JButton modifyBtn;
    JButton deleteBtn;
    
    public GeneralTableCell(JTable table, JFrame frm, int index) {
    	if(index == 7) {
    		modifyBtn = new JButton("수정");
        	
        	modifyBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Book selectedBook = null;;
					GeneralController generalController = new GeneralController();
					
					try {
						selectedBook = generalController.select(table.getSelectedRow());
					} catch (GeneralException e1) {
						e1.printStackTrace();
					}
					
					JFrame modifyFrm = new JFrame("책 정보 수정");
					
					modifyFrm.setSize(250, 200);
					modifyFrm.setLocationRelativeTo(null);
					
					modifyFrm.setLayout(new GridLayout(7, 2));
					
					JLabel titleLabel = new JLabel("제목", SwingConstants.RIGHT);
					JTextField titleText = new JTextField(selectedBook.getTitle());
					
					JLabel authorLabel = new JLabel("저자", SwingConstants.RIGHT);
					JTextField authorText = new JTextField(selectedBook.getAuthor());
					
					JLabel publisherLabel = new JLabel("출판사", SwingConstants.RIGHT);
					JTextField publisherText = new JTextField(selectedBook.getPublisher());
					
					JLabel dateLabel = new JLabel("출판년도", SwingConstants.RIGHT);
					JTextField dateText = new JTextField(selectedBook.getDate());
					
					JLabel stateLabel = new JLabel("상태", SwingConstants.RIGHT);
					String states[] = {"Excellent", "Good", "Fair"};
					JComboBox<String> stateBox = new JComboBox<String>(states);
					if(selectedBook.getState().equals("Excellent")){
						stateBox.setSelectedIndex(0);
					}else if(selectedBook.getState().equals("Good")) {
						stateBox.setSelectedIndex(1);
					}else {
						stateBox.setSelectedIndex(2);
					}
					
					JLabel priceLabel = new JLabel("가격", SwingConstants.RIGHT);
					JTextField priceText = new JTextField(selectedBook.getPrice());
					
					JButton confirmBtn = new JButton("확인");
					confirmBtn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							HashMap<String, String> result = new HashMap<String, String>();
							GeneralController generalController = new GeneralController();
							
							try {
								result = generalController.modify(table.getSelectedRow(), titleText.getText(), authorText.getText(), publisherText.getText(), dateText.getText(), stateBox.getSelectedItem().toString(), priceText.getText());
							} catch (GeneralException e1) {
								e1.printStackTrace();
							}
						
							if(result.get("message").equals("success")) {
								JOptionPane.showMessageDialog(frm, "수정되었습니다.", "Message", JOptionPane.INFORMATION_MESSAGE, null);
								
								modifyFrm.dispose();
								frm.dispose();
								try {
									generalController.showView();
								} catch (GeneralException e1) {
									e1.printStackTrace();
								}
							}else {
								JOptionPane.showMessageDialog(modifyFrm, result.get("message"), "Message", JOptionPane.INFORMATION_MESSAGE, null);
							}
						}
					});
					
					JButton cancleBtn = new JButton("취소");
					cancleBtn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							
							modifyFrm.dispose();
						}
					});
					
					modifyFrm.add(titleLabel);
					modifyFrm.add(titleText);
					
					modifyFrm.add(authorLabel);
					modifyFrm.add(authorText);
					
					modifyFrm.add(publisherLabel);
					modifyFrm.add(publisherText);
					
					modifyFrm.add(dateLabel);
					modifyFrm.add(dateText);
					
					modifyFrm.add(stateLabel);
					modifyFrm.add(stateBox);
					
					modifyFrm.add(priceLabel);
					modifyFrm.add(priceText);
					
					modifyFrm.add(confirmBtn);
					modifyFrm.add(cancleBtn);
					
					modifyFrm.setVisible(true);
					
					modifyFrm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

				}
			});	
    	}else {
    		deleteBtn = new JButton("삭제");
        	
    		deleteBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					GeneralController generalController = new GeneralController();
					
					int check =JOptionPane.showConfirmDialog(frm, "정말 삭제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
					
					if(check == JOptionPane.YES_OPTION) {
    					try {
							generalController.delete(table.getSelectedRow());
							
	    					frm.dispose();
	    					generalController.showView();
						} catch (GeneralException e1) {
							e1.printStackTrace();
						}
    				}
				}
			});
    	}
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	if(column == 7) {
    		return modifyBtn;	
    	}else {
    		return deleteBtn;
    	}
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    	if(column == 7) {
    		return modifyBtn;	
    	}else {
    		return deleteBtn;
    	}
    }
}
