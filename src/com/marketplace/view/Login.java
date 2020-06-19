package com.marketplace.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.marketplace.controller.LoginController;
import com.marketplace.controller.MainController;
import com.marketplace.controller.SignUpController;
import com.marketplace.exception.LoginException;
import com.marketplace.exception.MainException;
import com.marketplace.exception.SignUpException;

/** 
 * Class responsible for login page frontend view.
 * 
 * @date 2020.06.12
 * @author SimHongSub
 * @version 1.0
 */
public class Login {
	private JFrame frm;
	
	public Login() {
		frm = new JFrame("로그인");
	}

	public void show() {
		frm.setSize(300, 120);
		frm.setLocationRelativeTo(null);
		
		frm.setLayout(new GridLayout(3, 2));
		
		JLabel idLabel = new JLabel("아이디", SwingConstants.RIGHT);
		JTextField idText = new JTextField();
		
		JLabel pwLabel = new JLabel("비밀번호", SwingConstants.RIGHT);
		JPasswordField pwText = new JPasswordField();
		pwText.setEchoChar('*');
		
		JButton loginBtn = new JButton("로그인");
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<String, String> result = new HashMap<String, String>();
				LoginController loginController = new LoginController();
				
				try {
					result = loginController.loginCheck(idText.getText(), new String(pwText.getPassword()));
				} catch (LoginException e1) {
					e1.printStackTrace();
				}
				
				if(result.get("message").equals("success")) {
					MainController mainController = new MainController();
					
					frm.dispose();
					try {
						mainController.showView();
					} catch (MainException e1) {
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(frm, result.get("message"), "Message", JOptionPane.INFORMATION_MESSAGE, null);
				}
			}
		});
		
		JButton signUpBtn = new JButton("회원가입");
		signUpBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SignUpController signUpController = new SignUpController();
				
				frm.dispose();
				try {
					signUpController.showView();
				} catch (SignUpException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		frm.add(idLabel); 
		frm.add(idText);
		
		frm.add(pwLabel); 
		frm.add(pwText);
		
		frm.add(loginBtn);
		frm.add(signUpBtn);
		
		frm.setVisible(true);
		
		frm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
}
