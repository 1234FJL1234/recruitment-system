package com.itoffer.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itoffer.bean.User;
import com.itoffer.bean.Resume;
import com.itoffer.dao.ResumeDAO;

/**
 * ����������Ϣ����Servlet
 * 
 * @author TianYanan
 *
 */
@WebServlet("/ResumeServlet")
public class ResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ResumeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// �����������Ӧ����
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// ��ȡ�����������
		String type = request.getParameter("type");
		// ������Ӳ���
		if ("add".equals(type)) {
			// ��װ��������
			Resume basicinfo = this.requestDataObj(request);
			// �ӻỰ�����л�ȡ��ǰ��¼�û���ʶ
			User user = (User)request.getSession().getAttribute("SESSION_USER");
			// �����ݿ�����ӵ�ǰ�û��ļ���
			ResumeDAO dao = new ResumeDAO();
			int basicinfoID = dao.addBaseInfo(basicinfo, user.getUserId());
			request.getSession().setAttribute("SESSION_RESUMEID", basicinfoID);
			// �����ɹ���ת���ҵļ�����ҳ��
			response.sendRedirect("ResumeServlet?type=select");
		}else if("select".equals(type)){ // ������ѯ����
			// �ӻỰ�����л�ȡ��ǰ��¼�û���ʶ
			User User = (User)request.getSession().getAttribute("SESSION_USER");
			// ���ݼ�����ʶ��ѯ����������Ϣ
			ResumeDAO dao = new ResumeDAO();
			Resume basicinfo = dao.selectBasicinfoByID(User.getUserId());
			// ������������Ϣ����request�����������ת��
			request.setAttribute("basicinfo", basicinfo);
			request.getRequestDispatcher("applicant/resume.jsp").forward(request, response);
		}else if("updateSelect".equals(type)){ // ��������ǰ�Ĳ�ѯ
			// �ӻỰ�����л�ȡ��ǰ��¼�û���ʶ
			User User = (User)request.getSession().getAttribute("SESSION_USER");
			// ���ݼ�����ʶ��ѯ����������Ϣ
			ResumeDAO dao = new ResumeDAO();
			Resume basicinfo = dao.selectBasicinfoByID(User.getUserId());
			// ������������Ϣ����request�����������ת��
			request.setAttribute("basicinfo", basicinfo);
			request.getSession().setAttribute("completionOfResume", basicinfo.getCompletionOfResume());
			request.getRequestDispatcher("applicant/resumeBasicinfoUpdate.jsp").forward(request, response);
		}else if("update".equals(type)){
			// ��װ��������
			Resume basicinfo = this.requestDataObj(request);
			int basicinfoId = Integer.parseInt(request.getParameter("basicinfoId"));
			basicinfo.setBasicinfoId(basicinfoId);
			// ���¼�����Ϣ
			basicinfo.setResumeUpdate(basicinfo);
			request.setAttribute("basicinfo", basicinfo);
			request.getSession().setAttribute("completionOfResume", basicinfo.getCompletionOfResume());
			request.getRequestDispatcher("applicant/resumeBasicinfoUpdate.jsp").forward(request, response);
		}else if ("addEucation".equals(type)) {
			//��ȡ����id
			int basicinfoId = Integer.parseInt(request.getParameter("basicinfoId"));
			// ��װ��������
			Resume basicinfo = this.requestDataObj(request);
			// �����ݿ�����ӵ�ǰ�û��ļ���
			ResumeDAO dao = new ResumeDAO();
			dao.addEducationExperience(basicinfo, basicinfoId);
			request.getSession().setAttribute("completionOfResume", basicinfo.getCompletionOfResume());
			// �����ɹ���ת���ҵļ�����ҳ��
			response.sendRedirect("ResumeServlet?type=select");
		}else if ("addProject".equals(type)) {
			//��ȡ����id
			int basicinfoId = Integer.parseInt(request.getParameter("basicinfoId"));
			// ��װ��������
			Resume basicinfo = this.requestDataObj(request);
			// �����ݿ�����ӵ�ǰ�û��ļ���
			ResumeDAO dao = new ResumeDAO();
			dao.addProjectExperience(basicinfo, basicinfoId);
			request.getSession().setAttribute("completionOfResume", basicinfo.getCompletionOfResume());
			// �����ɹ���ת���ҵļ�����ҳ��
			response.sendRedirect("ResumeServlet?type=select");
		}else if ("resumeView".equals(type)) {
			// �ӻỰ�����л�ȡ��ǰ��¼�û���ʶ
			User User = (User)request.getSession().getAttribute("SESSION_USER");
			// ���ݼ�����ʶ��ѯ����������Ϣ
			ResumeDAO dao = new ResumeDAO();
			Resume basicinfo = dao.selectBasicinfoByID(User.getUserId());
			// ������������Ϣ����request�����������ת��
			request.setAttribute("basicinfo", basicinfo);
			request.getRequestDispatcher("applicant/resumeView.jsp").forward(request,response);
			/*response.sendRedirect("applicant/resumeView.jsp");*/
		}
	}

	/**
	 * ������ļ������ݷ�װ��һ������
	 * 
	 * @param request
	 * @return
	 * @throws ItOfferException
	 */
	private Resume requestDataObj(HttpServletRequest request) {
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
}
