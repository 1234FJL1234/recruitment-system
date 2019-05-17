package com.itoffer.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itoffer.bean.User;
import com.itoffer.dao.CompanyDAO;
import com.itoffer.dao.UserDAO;
import com.itoffer.util.CookieEncryptTool;

/**
 * ��ְ�ߵ�¼����ʵ��
 * 
 * @author TianYanan
 *
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
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
		PrintWriter out = response.getWriter();
		// ��ȡ�������
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");
		String requestPath = request.getParameter("requestPath");
		// ��¼��֤
		UserDAO dao = new UserDAO();
		CompanyDAO companyDAO = new CompanyDAO();
		User user = dao.login(email, password);
		int userID = user.getUserId();
		int userRole = user.getUserRole();
		
		if (userID != 0) {
			// ����Ϣ����session
			User sessionUser = new User(userID, email, password,userRole);
			request.getSession().setAttribute("SESSION_USER", sessionUser);
			// ͨ��Cookie��ס���������
			rememberMe(rememberMe, email, password, request, response);
			//�ж��Ƿ��Ѵ�������·��
			if(!"".equals(requestPath) && null!=requestPath){
				response.sendRedirect(requestPath);
			}else{
				if(userRole == 1){//�ض�����ͨ�û���¼ҳ��
					// �ж��Ƿ�����д����
					int resumeID = dao.isExistResume(userID);
					if (resumeID != 0){
						request.getSession().setAttribute("SESSION_RESUMEID", userID);
						response.sendRedirect("index.jsp");
					}else
						// �����������ڣ�����������д��ҳ��
						response.sendRedirect("applicant/resumeGuide.jsp");
				}else if(userRole == 2){//�ض�����ҵ�û���¼ҳ��
					// �ж��Ƿ�����д��ҵ��Ϣ
					int enterpriseInfoID = companyDAO.isExistEnterpriseInfo(userID);
					if (enterpriseInfoID != 0){
						request.getSession().setAttribute("SESSION_ENTERPRISEINFOID", enterpriseInfoID);
						// ����ҵ��Ϣ�Ѵ��ڣ�������ҵ�û�ҳ
						response.sendRedirect("CompanyServlet?type=viewEnterpriseInfo");
					}else
						// �����������ڣ�����������д��ҳ��
						///recruitment-system/WebContent/enterprise_user/addHrInfo.jsp
						response.sendRedirect("enterprise_user/addHrInfo.jsp");
				}else if(userRole == 3){//�ض�����ͷ�û���¼ҳ��
					// �ж��Ƿ�����д����
					int HeadhuntingInfoID = dao.isExistHeadhuntingInfo(userID);
					if (HeadhuntingInfoID != 0){
						request.getSession().setAttribute("SESSION_HEADHUNTINGINFOID", userID);
						int enterpriseID = companyDAO.isExistEnterpriseInfo(userID);
						if (enterpriseID != 0) {
							// ����ͷ��Ϣ�Ѵ��ڣ�������ͷ�û�����
							/*/recruitment-system/WebContent/headhunting_user/jsp/main.jsp*/
							response.sendRedirect("headhunting_user/jsp/main.jsp");
						}else {
							/*/recruitment-system/WebContent/headhunting_user/jsp/addCompany.jsp*/
							// ����ͷ��Ϣ�Ѵ��ڣ�������ҵ��Ϣ�Ǽ�ҳ
							response.sendRedirect("headhunting_user/jsp/addCompany.jsp");
						}
					}else
						// �����������ڣ�����������д��ҳ��
						response.sendRedirect("headhunting_user/headhuntingUserGuide.jsp");
				}else if(userRole == 4){//�ض���ϵͳ����Ա��¼ҳ��
					request.getSession().setAttribute("SESSION_HEADHUNTINGINFOID", userID);
					out.print("<script type='text/javascript'>");
					out.print("alert('���ǹ���Ա����ͨ������Ա��¼������¼!');");
					out.print("</script>");
					response.sendRedirect("system_administrator/administrator_login.jsp");
				}else{//�ض��򵽴���ҳ��
					response.sendRedirect("error.jsp");
				}
			}
		} else {
			// �û���¼��Ϣ���󣬽��д�����ʾ
			out.print("<script type='text/javascript'>");
			out.print("alert('�û���������������������룡');");
			out.print("window.location='login.jsp';");
			out.print("</script>");
		}
	}

	private void rememberMe(String rememberMe, String email, String password,
			HttpServletRequest request, HttpServletResponse response) {
		// �ж��Ƿ���Ҫͨ��Cookie��ס���������
		if ("true".equals(rememberMe)) {
			// ��ס���估����
			Cookie cookie = new Cookie("COOKIE_APPLICANTEMAIL",
					CookieEncryptTool.encodeBase64(email));
			cookie.setPath("/");
			cookie.setMaxAge(365 * 24 * 3600);
			response.addCookie(cookie);

			cookie = new Cookie("COOKIE_APPLICANTPWD",
					CookieEncryptTool.encodeBase64(password));
			cookie.setPath("/");
			cookie.setMaxAge(365 * 24 * 3600);
			response.addCookie(cookie);
		} else {
			// �����估����Cookie���
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("COOKIE_APPLICANTEMAIL".equals(cookie.getName())
							|| "COOKIE_APPLICANTPWD".equals(cookie.getName())) {
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}
			}
		}
	}

}
