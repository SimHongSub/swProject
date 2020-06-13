package com.marketplace.view;

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

import com.marketplace.controller.LoginController;

public class SignUp {
	
	public void show() {
		//회원가입 frame 생성
		JFrame frm = new JFrame("회원가입");
		
		//frame 사이즈, 화면상 위치 설정
		frm.setSize(300, 200);
		frm.setLocationRelativeTo(null);
		
		//frame layout설정
		frm.setLayout(new GridLayout(6, 2));
		
		//component 생성
		JLabel idLabel = new JLabel("아이디", SwingConstants.RIGHT);
		JTextField idText = new JTextField();
		
		JLabel pwLabel = new JLabel("비밀번호", SwingConstants.RIGHT);
		JPasswordField pwText = new JPasswordField();
		pwText.setEchoChar('*');
		
		JLabel nameLabel = new JLabel("이름", SwingConstants.RIGHT);
		JTextField nameText = new JTextField();
		
		JLabel phoneNumberLabel = new JLabel("전화번호", SwingConstants.RIGHT);
		JTextField phoneNumberText = new JTextField();
		
		JLabel emailLabel = new JLabel("이메일", SwingConstants.RIGHT);
		JTextField emailText = new JTextField();
		
		JButton confirmBtn = new JButton("확인");
		confirmBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginController loginController = new LoginController();
				
				frm.dispose();
				loginController.showView();
			}
		});
		
		//frame에 component 추가
		frm.add(idLabel); 
		frm.add(idText);
		
		frm.add(pwLabel); 
		frm.add(pwText);
		
		frm.add(nameLabel);
		frm.add(nameText);
		
		frm.add(phoneNumberLabel);
		frm.add(phoneNumberText);
		
		frm.add(emailLabel);
		frm.add(emailText);
		
		frm.add(confirmBtn);
		
		frm.setVisible(true);
		
		//x박스 클릭 action
		frm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);	
	}
}
