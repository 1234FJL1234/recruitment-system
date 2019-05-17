package com.itoffer.servlet.backstage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itoffer.bean.Company;
import com.itoffer.bean.Job;
import com.itoffer.bean.PageBean;
import com.itoffer.dao.CompanyDAO;
import com.itoffer.dao.JobDAO;
/**
 * ְλ��Ϣ����Servlet
 *
 * @author TianYanan
 *
 */
@WebServlet("/ManagerJobServlet")
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
		if("listByManager".equals(type)){
			getJobListByManager(request,response);
			// ����ת��
			request.getRequestDispatcher("system_administrator/jsp/jobList.jsp").forward(request, response);
		} else if("addByManager".equals(type)){
			getJobListByManager(request,response);
			int cid = Integer.parseInt(request.getParameter("companyId"));
			Job job = this.requestDataObj(request, cid);
			dao.addJobByUserId(job, cid);
			request.getRequestDispatcher("system_administrator/jsp/jobList.jsp").forward(request,response);
		} else if("updateByManager".equals(type)){
			getJobListByManager(request,response);
			
			int jId = Integer.parseInt(request.getParameter("jobId"));
			Job job=dao.getJobByID(jId);
			request.setAttribute("job", job);
			request.getRequestDispatcher("system_administrator/jsp/jobUpdate.jsp").forward(request,response);
		} else if("delectByManager".equals(type)){
			int jobId = Integer.parseInt(request.getParameter("jobId")) ;
			dao.deleteByManager(jobId);
			getJobListByManager(request,response);
			// ����ת��
			request.getRequestDispatcher("system_administrator/jsp/jobList.jsp").forward(request, response);
		} else if("updateJobByManager".equals(type)){
			//��ȡ��ҵ������Ϣ
			int jobId = Integer.parseInt(request.getParameter("jobId"));
			Company company = (Company) request.getAttribute("company");
			String jobName = request.getParameter("jobName");
			int jobHiringnum = Integer.parseInt(request.getParameter("jobHiringnum"));
			String jobSalary = request.getParameter("jobSalary");
			String jobArea = request.getParameter("jobArea");
			String jobAgeRequirements = request.getParameter("jobAgeRequirements");
			String jobEducationRequirements = request.getParameter("jobEducationRequirements");
			String jobSexRequirements = request.getParameter("jobSexRequirements");
			String jobForeignLanguages = request.getParameter("jobForeignLanguages");
			String jobDesc = request.getParameter("jobDesc");
			int jobState = (request.getParameter("jobState") == null) ? 1 : Integer.parseInt (request.getParameter("jobState")) ;
			Job job = new Job (jobId, company, jobName, jobHiringnum, jobSalary, jobArea,
					jobAgeRequirements, jobEducationRequirements, jobSexRequirements,
					jobForeignLanguages, jobDesc, jobState);
			dao.updateByManager(job);
			/*response.sendRedirect("CompanyServlet?type=listByManager");*/
			getJobListByManager(request,response);
			request.getRequestDispatcher("system_administrator/jsp/jobList.jsp").forward(request, response);
		}
	}
	
	private void getJobListByManager(HttpServletRequest request, HttpServletResponse response) {
		JobDAO dao = new JobDAO();
		CompanyDAO companyDAO=new CompanyDAO();
		//��ȡ����ҳ��
		int pageNo = Integer.parseInt(request.getParameter("pageNo")==null?"1":request.getParameter ("pageNo"));
		int recordCount=dao.getRecordCountByManager();
		List<Job> jobList=dao.getJobListByManager(pageNo,5);
		List<Company> companyNameList=companyDAO.selectALLCompanyName();
		
		//����ҳ��Ϣ��װ��pageJobBean������
		PageBean<Job> pageBean = new PageBean<Job>();
		pageBean.setPageNo(pageNo);
		pageBean.setRecordCount(recordCount);
		pageBean.setPageData(jobList);
		
		// ����ѯ����ҵ�б����request������
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("companyNameList", companyNameList);
	}
	
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
