package com.marketplace.model;

/** 
 * Model class responsible for save the user information.
 * 
 * @date 2020.06.12
 * @author SimHongSub
 * @version 1.0
 */
public class User {
	/**
	 * id - 사용자 ID
	 * password - 비밀번호
	 * name - 이름
	 * phoneNumber - 전화번호
	 * email - 이메일 주소
	 * authority - 권한(GENERAL, ADMIN)
	 * state - 상태(activated, deactivated)
	 */
	private String id;
	private String password;
	private String name;
	private String phoneNumber;
	private String email;
	private String authority;
	private String state;
	
	public User(String id, String password, String name, String phoneNumber, String email, String authority, String state) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.authority = authority;
		this.state = state;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
