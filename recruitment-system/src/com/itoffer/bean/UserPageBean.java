package com.itoffer.bean;

import java.util.ArrayList;
import java.util.List;

import com.itoffer.dao.UserDAO;

/**
 * �û��б��ҳ
 * @author TianYanan
 *
 */
public class UserPageBean {
	//ÿҳ��ʾ��¼��
	private int pageSize = 5;
	//��ǰҳ��
	private int pageNo = 1;
	//��ҳ��
	private int totalPages;
	//ÿҳ���ݼ�¼����
	private List<User> pageData = new ArrayList<User>();
	//�Ƿ��� ��һҳ
	private boolean hasNextPage;
	//�Ƿ�����һҳ
	private boolean hasPreviousPage;
	
	public UserPageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserPageBean(int pageSize, int pageNo, int totalPages, List<User> pageData, boolean hasNextPage,
			boolean hasPreviousPage) {
		super();
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.totalPages = totalPages;
		this.pageData = pageData;
		this.hasNextPage = hasNextPage;
		this.hasPreviousPage = hasPreviousPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPages() {
		// ��ȡ�ܼ�¼��
		UserDAO dao = new UserDAO();
		int recordCount = dao.getRecordCount();
		// ��ȡ��������ҳ��
		return (recordCount + pageSize - 1) / pageSize;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<User> getPageData() {
		// ��ѯ��ҳ��¼
		UserDAO dao = new UserDAO();
		List<User> list = dao.getUserPageList(pageNo, pageSize);
		return list;
	}

	public void setPageData(List<User> pageData) {
		this.pageData = pageData;
	}

	public boolean isHasNextPage() {
		return (this.getPageNo() < this.getTotalPages());
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPreviousPage() {
		return (this.getPageNo() > 1);
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
}
