package com.marketplace.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.marketplace.login.controller.LoginController;

public class Login {
	
	private JFrame frm;
	
	public Login() {
		frm = new JFrame("로그인");
	}

	public void show() {
		//로그인 frame 생성
		//JFrame frm = new JFrame("로그인");
		
		//frame 사이즈, 화면상 위치 설정
		frm.setSize(300, 150);
		frm.setLocationRelativeTo(null);
		
		//frame layout설정
		frm.setLayout(new GridLayout(3, 2));
		
		//component 생성
		JLabel idLabel = new JLabel("아이디", SwingConstants.RIGHT);
		JTextField idText = new JTextField(10);
		
		JLabel pwLabel = new JLabel("비밀번호", SwingConstants.RIGHT);
		JPasswordField pwText = new JPasswordField(10);
		pwText.setEchoChar('*');
		
		JButton loginBtn = new JButton("로그인");
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginController.loginClick();
				
			}
		});
		
		JButton signUpBtn = new JButton("회원가입");
		signUpBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginController.signUpClick();
				
			}
		});
		
		//frame에 component 추가
		frm.add(idLabel); 
		frm.add(idText);
		
		frm.add(pwLabel); 
		frm.add(pwText);
		
		frm.add(loginBtn);
		frm.add(signUpBtn);
		
		frm.setVisible(true);
		
		//x박스 클릭 action
		frm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public void close() {
		frm.dispose();	
	}
}
