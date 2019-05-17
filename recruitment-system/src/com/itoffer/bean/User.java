package com.itoffer.bean;

import java.util.Date;

/**
 * �û�ʵ����
 * 
 * @author TianYanan
 *
 */
public class User {
	// �û���ʶ
	private int userId;
	// �û�����
	private String userEmail;
	// �û�����
	private String userPwd;
	// �û���¼��
	private String userLogname;
	// �û���ʵ����
	private String userRealname;
	// �û���ɫ��1--��ͨ�û���2--��ҵ�û���3--��ͷ�û���4--ϵͳ����Ա
	private int userRole;
	// �û�״̬��1--���ã�2--����
	private int userState;
	
	private Resume resume;
	// �û�ע��ʱ��
	private Date userRegistDate;

	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	//����Ա�û�
	public User(String userEmail, String userPwd) {
		super();
		this.userEmail = userEmail;
		this.userPwd = userPwd;
	}
	
	//�û���¼
	public User(int userId, String userEmail, String userPwd,int userRole ) {
		super();
		this.userId = userId;
		this.userEmail = userEmail;
		this.userPwd = userPwd;
		this.userRole = userRole;
	}
	
	//ע��ʱʹ��
	public User(String userEmail, String userPwd, int userRole) {
		super();
		this.userEmail = userEmail;
		this.userPwd = userPwd;
		this.userRole = userRole;
	}

	//��ְ��ע��
	public User(int userId, String userEmail, String userPwd, int userRole, int userState, Date userRegistDate) {
		super();
		this.userId = userId;
		this.userEmail = userEmail;
		this.userPwd = userPwd;
		this.userRole = userRole;
		this.userState = userState;
		this.userRegistDate = userRegistDate;
	}
	
	//����Ա����û�
	public User(String userEmail, String userPwd, String userLogname, String userRealname, int userRole,
			int userState) {
		super();
		this.userEmail = userEmail;
		this.userPwd = userPwd;
		this.userLogname = userLogname;
		this.userRealname = userRealname;
		this.userRole = userRole;
		this.userState = userState;
	}
	
	//����Ա��¼
	public User(int userId, String userEmail, String userPwd, String userLogname, int userRole, int userState,
			Date userRegistDate) {
		super();
		this.userId = userId;
		this.userEmail = userEmail;
		this.userPwd = userPwd;
		this.userLogname = userLogname;
		this.userRole = userRole;
		this.userState = userState;
		this.userRegistDate = userRegistDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	

	public String getUserLogname() {
		return userLogname;
	}

	public void setUserLogname(String userLogname) {
		this.userLogname = userLogname;
	}

	public String getUserRealname() {
		return userRealname;
	}

	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public int getUserState() {
		return userState;
	}

	public void setUserState(int userState) {
		this.userState = userState;
	}

	public Date getUserRegistDate() {
		return userRegistDate;
	}

	public void setUserRegistDate(Date userRegistDate) {
		this.userRegistDate = userRegistDate;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}
}
