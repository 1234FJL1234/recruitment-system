package com.itoffer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itoffer.bean.Company;
import com.itoffer.bean.Job;
import com.itoffer.dao.CompanyDAO;
import com.itoffer.dao.JobDAO;
/**
 * ְλ��Ϣ����Servlet
 *
 * @author TianYanan
 *
 */
@WebServlet("/JobServlet")
public class JobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JobServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����������
		response.setContentType("text/html;charset=utf-8");
		//����������
		request.setCharacterEncoding("utf-8");
		//��Ӧ�������
		response.setCharacterEncoding("utf-8");

		// ��ȡ��������
		String type = request.getParameter("type");
		JobDAO dao = new JobDAO();
		CompanyDAO companyDAO = new CompanyDAO();
		if("select".equals(type)){
			// ��ȡְλ���
			int jobId = Integer.parseInt(request.getParameter("jobId"));
			// ����ְλ��Ų�ѯְλ��ϸ��Ϣ
			Job job = dao.getJobByID(jobId);
			// ��ְλ��Ϣ�������request����
			request.setAttribute("job", job);
			// ����ҵ��Ϣ�������request����
			request.setAttribute("company", job.getCompany());
			request.getRequestDispatcher("recruit/job.jsp").forward(request, response);
		} else if("addJobInfo".equals(type)) {
			int companyId = (int) request.getSession().getAttribute("SESSION_ENTERPRISEINFOID");
			Job job = this.requestDataObj(request,companyId);
			// �����ݿ�����ӵ�ǰ��ҵ����Ϣ
			int jobId = dao.addJobByUserId(job,companyId);
			Job jobView = dao.getJobByID(jobId);
			request.getSession().setAttribute("jobId", jobId);
			request.getSession().setAttribute("Job", jobView);
			// �����ɹ���ת����ҵ��Ϣ�鿴��ҳ��
			response.sendRedirect("JobServlet?type=viewJobInfo&jobId="+jobId);
		} else if("viewJobInfo".equals(type)){
			int companyId = (int) request.getSession().getAttribute("SESSION_ENTERPRISEINFOID");
			int jobId = Integer.parseInt(request.getParameter("jobId"));
			
			// ְλ��ϸ��Ϣ
			Job job = dao.getJobByID(jobId);
			/*Job job = (Job) request.getSession().getAttribute("Job");*/
			// ��ְλ��Ϣ�������request����
			request.setAttribute("job", job);
			// ����ҵ��Ϣ�������request����
			request.setAttribute("company", companyDAO.getCompanyByID(companyId));
			//recruitment-system/WebContent/enterprise_user/jsp/JobInfoView.jsp
			request.getRequestDispatcher("enterprise_user/jsp/JobInfoView.jsp").forward(request, response);
		} else if("updateJobInfo".equals(type)){
			// �ӻỰ�����л�ȡ��ǰְλ��ʶ
			int jobId = Integer.parseInt(request.getParameter("jobId"));
			int companyId = (int) request.getSession().getAttribute("SESSION_ENTERPRISEINFOID");
			Job job = this.requestDataObj(request,companyId);
			// �����ݿ�����ӵ�ǰ��ҵ����Ϣ
			dao.updateJobByJobId(job,jobId);
			request.getSession().setAttribute("jobId", jobId);
			request.setAttribute("job", job);
			// �����ɹ���ת����ҵ��Ϣ�鿴��ҳ��
			response.sendRedirect("JobServlet?type=viewJobInfo&jobId="+jobId);
		} else if("deleteJobInfo".equals(type)){
			// �ӻỰ�����л�ȡ��ǰְλ��ʶ
			int jobId = Integer.parseInt(request.getParameter("jobId"));
			dao.deleteByManager(jobId);
			// �����ɹ���ת����ҵ��Ϣ�鿴��ҳ��
			response.sendRedirect("CompanyServlet?type=viewEnterpriseInfo");
		}
	}
	
	/**
	 * �������ְλ��Ϣ���ݷ�װ��һ������
	 * 
	 * @param request
	 * @return
	 * @throws ItOfferException
	 */
	private Job requestDataObj(HttpServletRequest request,int companyId) {
		// �����������
		String jobName = "";
		if(request.getParameter("jobName")!=null){
			jobName = request.getParameter("jobName");
		}
		int jobHiringnum = 0;
		if(request.getParameter("jobHiringnum")!=null){
			jobHiringnum = Integer.parseInt(request.getParameter("jobHiringnum"));
		}
		String jobSalary = "";
		if(request.getParameter("jobSalary")!=null){
			jobSalary = request.getParameter("jobSalary");
		}
		String jobArea = "";
		if(request.getParameter("jobArea")!=null){
			jobArea = request.getParameter("jobArea");
		}
		String jobAgeRequirements = "";
		if(request.getParameter("jobAgeRequirements")!=null){
			jobAgeRequirements = request.getParameter("jobAgeRequirements");
		}
		String jobEducationRequirements = "";
		if(request.getParameter("jobEducationRequirements")!=null){
			jobEducationRequirements = request.getParameter("jobEducationRequirements");
		}
		String jobSexRequirements = "";
		if(request.getParameter("jobSexRequirements")!=null){
			jobSexRequirements = request.getParameter("jobSexRequirements");
		}
		String jobForeignLanguages = "";
		if(request.getParameter("jobForeignLanguages")!=null){
			jobForeignLanguages = request.getParameter("jobForeignLanguages");
		}
		String jobDesc = "";
		if(request.getParameter("jobDesc")!=null){
			jobDesc = request.getParameter("jobDesc");
		}
		int jobState = 0;
		if(request.getParameter("jobState")!=null){
			jobState =Integer.parseInt(request.getParameter("jobState"));
		}
		
		Company company = new Company();
		company.setCompanyId(companyId);
		
		// ���������ݷ�װ��һ����ҵ������Ϣ����
		Job Job= new Job(company, jobName, jobHiringnum, jobSalary, jobArea,
				jobAgeRequirements, jobEducationRequirements, jobSexRequirements,
				jobForeignLanguages, jobDesc, jobState);
		return Job;
	}
}
