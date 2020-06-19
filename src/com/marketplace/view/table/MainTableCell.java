package com.marketplace.view.table;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.marketplace.controller.MainController;
import com.marketplace.exception.MainException;
import com.marketplace.main.MarketPlace;

/** 
 * Class that creates a button in main page book list table.
 * 
 * @date 2020.06.12
 * @author SimHongSub
 * @version 1.0
 */
@SuppressWarnings("serial")
public class MainTableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer  {
	JButton purchaseBtn;
    JButton deleteBtn;
    
    public MainTableCell(JTable table, JFrame frm) {
    	if(MarketPlace.my.getAuthority().equals("ADMIN")) {
    		deleteBtn = new JButton("삭제");
    		deleteBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					MainController mainController = new MainController();
					
					int check =JOptionPane.showConfirmDialog(frm, "정말 삭제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
					
					if(check == JOptionPane.YES_OPTION) {
						try {
							mainController.delete(table.getSelectedRow());
							
							frm.dispose();
							mainController.showView();
						} catch (MainException e1) {
							e1.printStackTrace();
						}
    				}
				}
			});
    	}else {
    		purchaseBtn = new JButton("구입");
        	
        	purchaseBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					HashMap<String, String> result = new HashMap<String, String>();
					MainController mainController = new MainController();
					
					try {
						result = mainController.purchase(table.getSelectedRow());
					} catch (MainException e1) {
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(frm, result.get("message"), "Message", JOptionPane.INFORMATION_MESSAGE, null);
				}
			});	
    	}
    	
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
    	
    	if(MarketPlace.my.getAuthority().equals("ADMIN")) {
    		return deleteBtn;
    	}else {
            return purchaseBtn;	
    	}
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
            int column) {
    	
    	if(MarketPlace.my.getAuthority().equals("ADMIN")) {
    		return deleteBtn;
    	}else {

            return purchaseBtn;        		
    	}

    }
}
