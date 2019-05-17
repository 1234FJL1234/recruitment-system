package com.itoffer.servlet;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.itoffer.bean.Company;
import com.itoffer.bean.Resume;
import com.itoffer.bean.User;
import com.itoffer.dao.CompanyDAO;
import com.itoffer.dao.ResumeDAO;
import com.itoffer.dao.UserDAO;

/**
 * �û����
 * @author TianYanan
 *
 */
@WebServlet("/headhuntingServlet")
public class HeadhuntingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HeadhuntingServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		//��ȡ��������
		String type = request .getParameter ("type");
		ResumeDAO resumeDAO = new ResumeDAO();
		CompanyDAO companyDao = new CompanyDAO();
		if("addHeadhuntingInfo".equals(type)){
			// ��װ��������
			Resume basicinfo = this.requestHeadhuntingInfoDataObj(request);
			// �ӻỰ�����л�ȡ��ǰ��¼�û���ʶ
			User user = (User)request.getSession().getAttribute("SESSION_USER");
			// �����ݿ�����ӵ�ǰ�û��ļ���
			int basicinfoID = resumeDAO.addBaseInfo(basicinfo, user.getUserId());
			Resume headhuntingInfo = resumeDAO.selectBasicinfoByResumeID(basicinfoID);
			request.getSession().setAttribute("SESSION_HEADHUNTING_INFO", headhuntingInfo);
			//recruitment-system/WebContent/headhunting_user/jsp/addCompany.jsp
			response.sendRedirect("headhunting_user/jsp/addCompany.jsp");
		} else if("addEnterpriseInfo".equals(type)){
			Company company = this.requestEnterpriseInfoDataObj(request);
			// �ӻỰ�����л�ȡ��ǰ��¼�û���ʶ
			User user = (User)request.getSession().getAttribute("SESSION_USER");
			// �����ݿ�����ӵ�ǰ��ҵ����Ϣ
			company.setCompanyHrId(user.getUserId());
			int companyId = companyDao.addCompanyByUserId(company);
			Company enterpriseInfo = companyDao.getCompanyByUserID(companyId);
			request.getSession().setAttribute("SESSION_ENTERPRISE_INFO", enterpriseInfo);
			// �����ɹ���ת����ҵ��Ϣ�鿴��ҳ��
			response.sendRedirect("headhuntingServlet?type=viewHeadhuntingInfo");
		} else if("viewHeadhuntingInfo".equals(type)){
			// �ӻỰ�����л�ȡ��ǰ��¼�û���ʶ
			User user = (User)request.getSession().getAttribute("SESSION_USER");
			// �ӻỰ�����л�ȡ�û�������Ϣ
			Resume headhuntingInfo = resumeDAO.selectBasicinfoByID(user.getUserId());
			// �ӻỰ�����л�ȡ�û�������ҵ������Ϣ
			Company enterpriseInfo = companyDao.getCompanyByUserID(user.getUserId());
			request.setAttribute("basicinfo", headhuntingInfo);
			request.setAttribute("enterprise", enterpriseInfo);
			// �����ɹ���ת����ͷ��Ϣ�鿴��ҳ��
			/*/recruitment-system/WebContent/headhunting_user/jsp/main.jsp*/
			request.getRequestDispatcher("headhunting_user/jsp/headhuntingInfoView.jsp").forward(
					request, response);
		}
	}
	
	/**
	 * ������ļ������ݷ�װ��һ������
	 * 
	 * @param request
	 * @return
	 * @throws ItOfferException
	 */
	private Resume requestHeadhuntingInfoDataObj(HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Resume basicinfo = null;
		// �����������
		int userId = 0;
		if(request.getParameter("userId")!=null){
			userId =Integer.parseInt(request.getParameter("userId"));
		}
		String realname = "";
		if(request.getParameter("realName")!=null){
			realname = request.getParameter("realName");
		}
		String gender = "";
		if(request.getParameter("gender")!=null){
			gender = request.getParameter("gender");
		}
		String birthday = "";
		Date birthdayDate = null;
		if(request.getParameter("birthday")!=null){
			birthday = request.getParameter("birthday");
			try {
				birthdayDate = sdf.parse(birthday);
			} catch (ParseException e) {
				birthdayDate = null;
			}
		}
		String currentLoca = "";
		if(request.getParameter("currentLoca")!=null){
			currentLoca = request.getParameter("currentLoca");
		}
		String residentLoca = "";
		if(request.getParameter("residentLoca")!=null){
			residentLoca = request.getParameter("residentLoca");
		}
		String telephone = "";
		if(request.getParameter("telephone")!=null){
			telephone = request.getParameter("telephone");
		}
		String email = "";
		if(request.getParameter("email")!=null){
			email = request.getParameter("email");
		}
		String jobIntension = "";
		if(request.getParameter("jobIntension")!=null){
			jobIntension = request.getParameter("jobIntension");
		}
		String jobExperience = "";
		if(request.getParameter("jobExperience")!=null){
			jobExperience = request.getParameter("jobExperience");
		}
		String headShot = "";
		if(request.getParameter("headShot")!=null){
			headShot = request.getParameter("headShot");
		}
		String graduateSchool = "";
		if(request.getParameter("graduateSchool")!=null){
			graduateSchool = request.getParameter("graduateSchool");
		}
		String studyStartTime = "";
		Date studyTime = null;
		if(request.getParameter("studyStartTime")!=null){
			studyStartTime = request.getParameter("studyStartTime");
			try {
				studyTime = sdf.parse(studyStartTime);
			} catch (ParseException e) {
				studyTime = null;
			}
		}
		String education = "";
		if(request.getParameter("education")!=null){
			education =request.getParameter("education");
		}
		String major = "";
		if(request.getParameter("major")!=null){
			major = request.getParameter("major");
		}
		String entryName = "";
		if(request.getParameter("entryName")!=null){
			entryName = request.getParameter("entryName");
		}
		String entryStartTime = "";
		Date entryStart = null;
		if(request.getParameter("entryStartTime")!=null){
			entryStartTime = request.getParameter("entryStartTime");
			try {
				entryStart = sdf.parse(entryStartTime);
			} catch (ParseException e) {
				entryStart = null;
			}
		}
		String entryStopTime = "";
		Date entryStop = null;
		if(request.getParameter("entryStopTime")!=null){
			entryStopTime = request.getParameter("entryStopTime");
			try {
				entryStop = sdf.parse(entryStopTime);
			} catch (ParseException e) {
				entryStop = null;
			}
		}
		String assumeOffice = "";
		if(request.getParameter("assumeOffice")!=null){
			assumeOffice = request.getParameter("assumeOffice");
		}
		String resumeEnclosure = "";
		if(request.getParameter("resumeEnclosure")!=null){
			resumeEnclosure = request.getParameter("resumeEnclosure");
		}
		
		// ���������ݷ�װ��һ������������Ϣ����
		basicinfo = new Resume(userId,realname, gender, birthdayDate,
				currentLoca, residentLoca, telephone, email, jobIntension,
				jobExperience,headShot,graduateSchool,studyTime,
				education,major,entryName,entryStart,entryStop,
				assumeOffice,resumeEnclosure);
		return basicinfo;
	}
	
	
	/**
	 * ���������ҵ��Ϣ���ݷ�װ��һ������
	 * 
	 * @param request
	 * @return
	 * @throws ItOfferException
	 */
	private Company requestEnterpriseInfoDataObj(HttpServletRequest request) {
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
				companyViewnum, companyTel);
		return company;
	}
}
