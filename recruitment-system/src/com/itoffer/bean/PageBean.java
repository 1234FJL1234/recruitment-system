package com.itoffer.bean;

import java.util.ArrayList;
import java.util.List;

import com.itoffer.dao.CompanyDAO;

public class PageBean<T> {
		// ÿҳ��ʾ��¼��
		private int pageSize = 5;
		// ��ǰҳ��
		private int pageNo = 1;
		// ��ҳ��
		private int totalPages;
		//�ܼ�¼��
		private int recordCount;
		// ÿҳ���ݼ�¼����
		private List<T> pageData = new ArrayList<T>();
		// �Ƿ�����һҳ
		private boolean hasNextPage;
		// �Ƿ�����һҳ
		private boolean hasPreviousPage;

		public PageBean() {
		}

		public PageBean(int pageSize, int pageNo) {
			this.pageSize = pageSize;
			this.pageNo = pageNo;
		}

		public int getTotalPages() {
			/*// ��ȡ�ܼ�¼��
			CompanyDAO dao = new CompanyDAO();
			int recordCount = dao.getRecordCount();*/
			// ��ȡ��������ҳ��
			return (recordCount + pageSize - 1) / pageSize;
		}

		public void setTotalPages(int totalPages) {
			this.totalPages = totalPages;
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
		
		public int getRecordCount() {
			return recordCount;
		}

		public void setRecordCount(int recordCount) {
			this.recordCount = recordCount;
		}

		/**
		 * ��̨��ȡ���ݣ�����Ա��ҵ�б���ʾ��
		 * @return
		 */
		public List<T> getPageData() {
			/*// ��ѯ��ҳ��¼
			CompanyDAO dao = new CompanyDAO();
			List<Company> list = dao.getALLCompanyList2(pageNo, pageSize);
			return list;*/
			return pageData;
		}

		public void setPageData(List<T> pageData) {
			this.pageData = pageData;
		}
		
		/*public void setPageData(List<T> pageData,List<T> pageData2) {
			this.pageData = pageData;
			
		}*/

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
