package com.itoffer.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import com.itoffer.dao.CompanyDAO;
import com.itoffer.dao.JobDAO;
/**
 * ��ҵְλ��Ϣ�������ͳ�Ƽ�����
 * @author TianYanan
 *
 */
@WebListener
public class JobViewCountListener implements ServletRequestListener {

	public JobViewCountListener() {

	}

	public void requestDestroyed(ServletRequestEvent sre) {

	}

	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest request = (HttpServletRequest) sre
				.getServletRequest();
		String requestURI = request.getRequestURI();
		String queryString = request.getQueryString() == null ? "" : request
				.getQueryString();
		// �ж��Ƿ�������ҵְλ����Servlet���������󣬲��Һ��б�ʾ��ҵְλ��Ϣ�鿴���������
		if (requestURI.indexOf("JobServlet") >= 0
				&& (queryString.indexOf("select") >= 0)) {
			// �������ַ��������л�ȡ��ҵְλ���
			int id = Integer.parseInt(queryString.substring(queryString
					.lastIndexOf('=') + 1));
			// ���´���ҵְλ��Ϣ���������
			JobDAO dao = new JobDAO();
			dao.updateJobViewCount(id);
		}
	}

}
