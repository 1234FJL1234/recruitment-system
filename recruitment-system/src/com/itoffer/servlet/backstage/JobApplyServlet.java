package com.itoffer.servlet.backstage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itoffer.bean.JobApply;
import com.itoffer.bean.Job;
import com.itoffer.bean.JobApply;
import com.itoffer.bean.PageBean;
import com.itoffer.dao.JobApplyDAO;
import com.itoffer.dao.JobApplyDAO;
import com.itoffer.dao.JobDAO;

/**
 * ְλ���봦��Servlet
 * 
 * @author TianYanan
 *
 */
@WebServlet("/ManagerJobApplyServlet")
public class JobApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JobApplyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����������ݱ���
		request.setCharacterEncoding("UTF-8");
		// ������Ӧ�ĵ����ͺͱ���
		response.setContentType("text/plain;charset=UTF-8");
		JobApplyDAO dao = new JobApplyDAO();
		// ��ȡ�����������
		String type = request.getParameter("type");
		if ("listByManager".equals(type)) {
			//��ȡ����ҳ��
			int pageNo = Integer.parseInt(request.getParameter("pageNo")==null?"1":request.getParameter ("pageNo"));
			int recordCount=dao.getRecordCountByManager();
			List<JobApply> jobApplyList=dao.getALLJobApplyListByManager(pageNo,5);
			
			//����ҳ��Ϣ��װ��PageBean������
			PageBean<JobApply> pageBean = new PageBean<JobApply>() ;
			pageBean.setPageNo(pageNo);
			pageBean.setRecordCount(recordCount);
			pageBean.setPageData(jobApplyList);
			
			// ����ѯ����ҵ�б����request������
			request.setAttribute("pageBean", pageBean);
			request.getRequestDispatcher("system_administrator/jsp/applyList.jsp").forward(request,response);
		} else if ("deleteApplyByManager".equals(type)) {
			int applyId = Integer.parseInt(request.getParameter("applyId")) ;
			dao.deleteByManager(applyId);
			
			request.getRequestDispatcher("system_administrator/jsp/applyList.jsp").forward(request,response);
		}
	}
}