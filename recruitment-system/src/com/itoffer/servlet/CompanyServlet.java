package com.itoffer.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.itoffer.bean.CompanyPageBean;
import com.itoffer.bean.Company;
import com.itoffer.bean.Job;
import com.itoffer.bean.Resume;
import com.itoffer.bean.User;
import com.itoffer.dao.CompanyDAO;
import com.itoffer.dao.JobDAO;
import com.itoffer.dao.ResumeDAO;
import com.itoffer.dao.UserDAO;

/**
 * ��ҵ��Ϣ����Servlet
 * 
 * @author TianYanan
 *
 */
@WebServlet("/CompanyServlet")
//�ϴ��ļ�ע��
@MultipartConfig
public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CompanyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//�����������
		response.setContentType("text/html;charset=utf-8");
		//����������
		request.setCharacterEncoding("utf-8");
		//��Ӧ�������
		response.setCharacterEncoding("utf-8");
		//��ȡ��Ӧ�ַ������
		PrintWriter out = response.getWriter();
		// ��ȡ����ҵ��Ϣ�������������
		String type = request.getParameter("type");
		CompanyDAO companyDao = new CompanyDAO();
		JobDAO jobdao = new JobDAO();
		UserDAO userDao = new UserDAO();
		if("login".equals (type)) {
			//��ȡ�û��ύ����֤��
			String validateCode = request.getParameter("validateCode");
			//��ȡ��������Ự�б������֤��
			String sessionValidateCode = (String)request.getSession().
			getAttribute("SESSION_VALIDATECODE") ;
			//�ж�����֤�벻--�£���ʾ���󣬷��ص�½ҳ��
			if (! sessionValidateCode.equals (validateCode) ) {
				out.print("<script type='text/javascript'>") ;
				out.print("alert('��֤���������! ' ;") ;
				out.print ("window.location='system_administrator/administrator_login.html';") ;
				out.print("</script>") ;
				return ;
			}
		} else if("selectById".equals(type)) {
			// ��ȡ�����ѯ����ҵ���
			int companyID = Integer.parseInt(request.getParameter("id"));
			// ������ҵ��Ų�ѯ��ҵ��ϸ��Ϣ
			Company company = companyDao.getCompanyByID(companyID);
			// ����ѯ������ҵ��Ϣ����request������
			request.setAttribute("company", company);
			// ������ҵ��Ų�ѯ��ҵ��������Ƹְλ
			List<Job> jobList = jobdao.getJobListByCompanyID(companyID);
			// ����ѯ����ְλ�б����request������
			request.setAttribute("joblist", jobList);
			// ����ת��
			request.getRequestDispatcher("recruit/company.jsp").forward(
					request, response);
		} else if("pageList".equals(type)) {
			// ��ҵ�б��ҳ��ʾ����
			String pageNo = request.getParameter("pageNo");
			if (pageNo == null || "".equals(pageNo))
				pageNo = "1";
			// ��ҵ��Ϣ��ҳչʾ����ʵ��JavaBean
			CompanyPageBean pagination = new CompanyPageBean(2,
					Integer.parseInt(pageNo));
			request.setAttribute("pagination", pagination);
			request.getRequestDispatcher("include_companyList.jsp").include(
					request, response);
		} else if("addEnterpriseInfo".equals(type)) {
			Company company = this.requestDataObj(request);
			// �ӻỰ�����л�ȡ��ǰ��¼�û���ʶ
			User user = (User)request.getSession().getAttribute("SESSION_USER");
			// �����ݿ�����ӵ�ǰ��ҵ����Ϣ
			company.setCompanyHrId(user.getUserId());
			int companyId = companyDao.addCompanyByUserId(company);
			Company companyView = companyDao.getCompanyByUserID(companyId);
			request.getSession().setAttribute("SESSION_ENTERPRISEINFOID", companyId);
			request.getSession().setAttribute("company", companyView);
			// �����ɹ���ת����ҵ��Ϣ�鿴��ҳ��
			response.sendRedirect("CompanyServlet?type=viewEnterpriseInfo");
		} else if("viewEnterpriseInfo".equals(type)) {
			// �ӻỰ�����л�ȡ��ǰ��¼�û���ʶ
			User user = (User)request.getSession().getAttribute("SESSION_USER");
			User selectUser = userDao.selectById(user.getUserId());
			request.setAttribute("user", selectUser);
			// ������ҵ��Ų�ѯ��ҵ��ϸ��Ϣ
			Company company = companyDao.getCompanyByUserID(user.getUserId());
			// ����ѯ������ҵ��Ϣ����request������
			request.setAttribute("company", company);
			// ������ҵ��Ų�ѯ��ҵ��������Ƹְλ
			List<Job> jobList = jobdao.getJobListByCompanyID(company.getCompanyId());
			// ����ѯ����ְλ�б����request������
			request.setAttribute("joblist", jobList);
			// ����ת��
			request.getRequestDispatcher("enterprise_user/jsp/enterpriseInfoView.jsp").forward(
					request, response);
		} else if("updateEnterpriseInfo".equals(type)) {
				Company company = this.requestDataObj(request);
				// �ӻỰ�����л�ȡ��ǰ��¼�û���ʶ
				int companyId = Integer.parseInt(request.getParameter("companyId"));
				int companyHrId = Integer.parseInt(request.getParameter("companyHrId"));
				company.setCompanyId(companyId);
				company.setCompanyHrId(companyHrId);
				// �����ݿ�����ӵ�ǰ��ҵ����Ϣ
				companyDao.updateByManager(company);
				/*Company companyView = companyDao.getCompanyByUserID(companyId);
				request.getSession().setAttribute("company", companyView);*/
				// �����ɹ���ת����ҵ��Ϣ�鿴��ҳ��
				response.sendRedirect("CompanyServlet?type=viewEnterpriseInfo");
		} else if ("resumeView".equals(type)) {
			int userId = Integer.parseInt(request.getParameter("userId"));
			// ���ݼ�����ʶ��ѯ����������Ϣ
			ResumeDAO dao = new ResumeDAO();
			Resume basicinfo = dao.selectBasicinfoByID(userId);
			// ������������Ϣ����request�����������ת��
			request.setAttribute("basicinfo", basicinfo);
			request.getRequestDispatcher("applicant/resumeView.jsp").forward(request,response);
		}
	}
	
	/**
	 * ���������ҵ��Ϣ���ݷ�װ��һ������
	 * 
	 * @param request
	 * @return
	 * @throws ItOfferException
	 */
	private Company requestDataObj(HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// �����������
		String companyName = "";
		if(request.getParameter("companyName")!=null){
			companyName = request.getParameter("companyName");
		}
		String companyArea = "";
		if(request.getParameter("companyArea")!=null){
			companyArea = request.getParameter("companyArea");
		}
		String companySize = "";
		if(request.getParameter("companySize")!=null){
			companySize = request.getParameter("companySize");
		}
		String companyType = "";
		if(request.getParameter("companyType")!=null){
			companyType = request.getParameter("companyType");
		}
		String companyBrief = "";
		if(request.getParameter("companyBrief")!=null){
			companyBrief = request.getParameter("companyBrief");
		}
		int companyState = 0;
		if(request.getParameter("companyState")!=null){
			companyState =Integer.parseInt(request.getParameter("companyState"));
		}
		int companySort = 0;
		if(request.getParameter("companySort")!=null){
			companySort = Integer.parseInt(request.getParameter("companySort"));
		}
		int companyViewnum = 0;
		if(request.getParameter("companyViewnum")!=null){
			companyViewnum = Integer.parseInt(request.getParameter("companyViewnum"));
		}
		
		// ��ȡ�ϴ��ļ���
		Part part = null;
		try {
			part = request.getPart("companyPic");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// ��ȡ�ϴ��ļ�����
		String fileName = part.getSubmittedFileName();
		// Ϊ��ֹ�ϴ��ļ����������ļ�����������
		String newFileName = System.currentTimeMillis() + fileName;
		// ���ϴ����ļ������ڷ�������Ŀ����·����/file/upload_resumeĿ¼��
		String filepath = getServletContext().getRealPath("/file/upload_companypic");
		System.out.println("��ҵͼƬ����·��Ϊ��" + filepath);
		File f = new File(filepath);
		if (!f.exists())
			f.mkdirs();
		try {
			part.write(filepath + "/" + newFileName);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String companyRegisterTime = "";
		Date registerTime = null;
		if(request.getParameter("companyRegisterTime")!=null){
			companyRegisterTime = request.getParameter("companyRegisterTime");
			try {
				registerTime = sdf.parse(companyRegisterTime);
			} catch (ParseException e) {
				registerTime = null;
			}
		}
		int companyRegisterCapital = 0;
		if(request.getParameter("companyRegisterCapital")!=null){
			companyRegisterCapital = Integer.parseInt(request.getParameter("companyRegisterCapital"));
		}
		
		String companyLegalPerson = "";
		if(request.getParameter("companyLegalPerson")!=null){
			companyLegalPerson = request.getParameter("companyLegalPerson");
		}
		String companyCreditCode = "";
		if(request.getParameter("companyCreditCode")!=null){
			companyCreditCode = request.getParameter("companyCreditCode");
		}
		String companyTel = "";
		if(request.getParameter("companyTel")!=null){
			companyTel = request.getParameter("companyTel");
		}
		
		String companyIndustry = "";
		if(request.getParameter("companyIndustry")!=null){
			companyIndustry = request.getParameter("companyIndustry");
		}
		
		
		// ���������ݷ�װ��һ����ҵ������Ϣ����
		Company company= new Company(companyName, registerTime, companyRegisterCapital,
				companyLegalPerson, companyCreditCode, companyArea, companySize,
				companyType, companyIndustry, companyBrief, companyState, companySort,
				companyViewnum, newFileName, companyTel);
		return company;
	}
}
