package com.itoffer.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itoffer.bean.User;
import com.itoffer.bean.JobApply;
import com.itoffer.bean.PageBean;
import com.itoffer.dao.JobApplyDAO;

/**
 * ְλ���봦��Servlet
 * 
 * @author TianYanan
 *
 */
@WebServlet("/JobApplyServlet")
public class JobApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JobApplyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ��������
		String type = request.getParameter("type");
		JobApplyDAO dao = new JobApplyDAO();
		if ("apply".equals(type)) {
			// ��ȡְλ���
			int jobId = Integer.parseInt(request.getParameter("jobId"));
			// ��ȡ��¼�û�
			User User = (User) request.getSession()
					.getAttribute("SESSION_USER");
			// ��Ӵ��û��Դ�ְλ������
			dao.save(jobId, User.getUserId());
			response.sendRedirect("JobApplyServlet?type=myapply");
		} else if ("myapply".equals(type)) {
			// ��ȡ��¼�û�
			User User = (User) request.getSession()
					.getAttribute("SESSION_USER");
			// �����û���ʶ��ѯ���û����������ְλ
			List<JobApply> jobList = dao.getJobApplyList(User
					.getUserId());
			request.setAttribute("jobList", jobList);
			request.getRequestDispatcher("applicant/jobApply.jsp").forward(
					request, response);
		}if ("listByUser".equals(type)) {
			// ��ȡְλ���
			int jobId = Integer.parseInt(request.getParameter("jobId"));
			//��ȡ����ҳ��
			int pageNo = Integer.parseInt(request.getParameter("pageNo")==null?"1":request.getParameter ("pageNo"));
			int recordCount=dao.getRecordCountByManager();
			List<JobApply> jobApplyList=dao.getJobApplyListByUser(pageNo,5,jobId);
			
			//����ҳ��Ϣ��װ��PageBean������
			PageBean<JobApply> pageBean = new PageBean<JobApply>();
			pageBean.setPageNo(pageNo);
			pageBean.setRecordCount(recordCount);
			pageBean.setPageData(jobApplyList);
			
			// ����ѯ����ҵ�б����request������
			request.setAttribute("pageBean", pageBean);
			/*/recruitment-system/WebContent/enterprise_user/jsp/applyList.jsp*/
			request.getRequestDispatcher("enterprise_user/jsp/applyList.jsp").forward(request,response);
		}
	}

}
