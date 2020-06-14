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

import com.marketplace.controller.AdminController;

@SuppressWarnings("serial")
public class AdminTableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer  {
	JButton deleteBtn;
    JButton stateBtn;
    
    public AdminTableCell(JTable table, JFrame frm, int index) {
    	if(index == 6) {
    		deleteBtn = new JButton("삭제");
    		deleteBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					HashMap<String, String> result = new HashMap<String, String>();
					AdminController adminController = new AdminController();
					
					result = adminController.checkState(table.getSelectedRow());
					
					if(result.get("message").equals("success")) {
						int check =JOptionPane.showConfirmDialog(frm, "정말 삭제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
    					
    					if(check == JOptionPane.YES_OPTION) {
    						adminController.delete(table.getSelectedRow());
    						
    						frm.dispose();
    						adminController.showView();
        				}
					}else {
						
						JOptionPane.showMessageDialog(frm, "사용자가 activated 상태입니다.", "Message", JOptionPane.INFORMATION_MESSAGE, null);
					}
				}
			});
    	}else {
        	stateBtn = new JButton("상태 수정");
        	stateBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					AdminController adminController = new AdminController();
					adminController.modify(table.getSelectedRow());
					
					JOptionPane.showMessageDialog(frm, "사용자 상태가 수정되었습니다.", "Message", JOptionPane.INFORMATION_MESSAGE, null);
					
					frm.dispose();
					adminController.showView();
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

    	if(column == 6) {
    		
    		return deleteBtn;
    	}else {
    		/*AdminController adminController = new AdminController();
    		User user;
    		
    		user = adminController.select(row);
    		
    		stateBox.setSelectedItem(user.getState());*/
    		
    		return stateBtn;
    	}
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

    	if(column == 6) {
    		
    		return deleteBtn;
    	}else {
    		/*AdminController adminController = new AdminController();
    		User user;
    		
    		user = adminController.select(row);
    		
    		stateBox.setSelectedItem(user.getState());*/
    		
    		return stateBtn;
    	}
    }
}
