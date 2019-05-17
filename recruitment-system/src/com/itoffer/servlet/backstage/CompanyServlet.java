package com.itoffer.servlet.backstage;

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

import com.itoffer.bean.Company;
import com.itoffer.bean.PageBean;
import com.itoffer.dao.CompanyDAO;

/**
 * ��ҵ��Ϣ����Servlet
 * 
 * @author TianYanan
 *
 */
@WebServlet("/ManagerCompanyServlet")
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
		if("listByManager".equals(type)) {
			getCompanyListByManager(request,response);
			
			// ����ת��
			request.getRequestDispatcher("system_administrator/jsp/companyList.jsp").forward(request, response);
		} else if("addByManager".equals(type)){
			Company company = this.requestDataObj(request);
			int companyHrId = 0;
			if(request.getParameter("companyHrId")!=null){
				companyHrId =Integer.parseInt(request.getParameter("companyHrId"));
			}
			company.setCompanyHrId(companyHrId);
			companyDao.addCompanyByUserId(company);
			getCompanyListByManager(request,response);
			request.getRequestDispatcher("system_administrator/jsp/companyList.jsp").forward(request,response);
		} else if("delectByManager".equals(type)){
			getCompanyListByManager(request,response);
			int companyId = Integer.parseInt(request.getParameter("companyId")) ;
			companyDao.deleteByManager(companyId);
			
			request.getRequestDispatcher("system_administrator/jsp/companyList.jsp").forward(request,response);
		} else if("updateByManager".equals(type)){
			getCompanyListByManager(request,response);
			
			int companyId = Integer.parseInt(request.getParameter("companyId"));
			Company company=companyDao.getCompanyByID(companyId);
			request.setAttribute("company", company);
			request.getRequestDispatcher("system_administrator/jsp/companyUpdate.jsp").forward(request,response);
		} else if("updateCompanyByManager".equals(type)){
			//��ȡ��ҵ������Ϣ
			Company company = this.requestDataObj(request);
			int companyId = Integer.parseInt(request.getParameter("companyId"));
			company.setCompanyId(companyId);
			companyDao.updateByManager(company);
			getCompanyListByManager(request,response);
			request.getRequestDispatcher("system_administrator/jsp/companyList.jsp").forward(request, response);
		}
	}

	private void getCompanyListByManager(HttpServletRequest request, HttpServletResponse response) {
		CompanyDAO companyDao = new CompanyDAO();
		
		//��ȡ����ҳ��
		int pageNo = Integer.parseInt(request.getParameter("pageNo")==null?"1":request.getParameter ("pageNo"));
		int recordCount=companyDao.getRecordCountByManager();
		List<Company> companyList=companyDao.getALLCompanyListByManager(pageNo,5);
		
		//����ҳ��Ϣ��װ��PageBean������
		PageBean<Company> pageBean = new PageBean<Company>() ;
		pageBean.setPageNo(pageNo);
		pageBean.setRecordCount(recordCount);
		pageBean.setPageData(companyList);
		
		// ����ѯ����ҵ�б����request������
		request.setAttribute("pageBean", pageBean);
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
