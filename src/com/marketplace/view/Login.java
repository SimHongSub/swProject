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

public class Login {
	//로그인 frame
	private JFrame frm;
	
	public Login() {
		frm = new JFrame("로그인");
	}

	public void show() {
		//frame 사이즈, 화면상 위치 설정
		frm.setSize(300, 120);
		frm.setLocationRelativeTo(null);
		
		//frame layout설정
		frm.setLayout(new GridLayout(3, 2));
		
		//component 생성
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
				
				result = loginController.loginCheck(idText.getText(), new String(pwText.getPassword()));
				
				if(result.get("message").equals("success")) {
					MainController mainController = new MainController();
					
					frm.dispose();
					mainController.showView();
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
				signUpController.showView();
				
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
}
