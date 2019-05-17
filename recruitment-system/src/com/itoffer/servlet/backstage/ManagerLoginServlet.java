package com.itoffer.servlet.backstage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itoffer.bean.User;
import com.itoffer.dao.UserDAO;

/**
 * ��ְ�ߵ�¼����ʵ��
 * 
 * @author TianYanan
 *
 */
@WebServlet("/ManagerLoginServlet")
public class ManagerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ManagerLoginServlet() {
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
		String email = request.getParameter("userEmail");
		String password = request.getParameter("userPwd");
		//��ȡ��������
		String type = request.getParameter("type");
		if("managerLogin".equals(type)){
			//��ȡ�û��ύ����֤��
			String validateCode = request.getParameter("validateCode");
			// �ж���֤���Ƿ���ȷ
			String sessionValidateCode = (String)request.getSession().getAttribute("SESSION_VALIDATECODE");
			if(!sessionValidateCode.equals(validateCode)){
				out.print("<script type='text/javascript'>");
				out.print("alert('��������ȷ��֤�룡');");
				out.print("window.location = 'system_administrator/administrator_login.jsp'");
				out.print("</script>");
				return;
			}else{
				// ��¼��֤
				UserDAO dao = new UserDAO();
				User LoginUser = dao.managerLogin(email, password);
				int userID = LoginUser.getUserId();
				int userRole = LoginUser.getUserRole();
				if (userID != 0) {
					User sessionUser = new User(userID, email, password,userRole);
					request.getSession().setAttribute("SESSION_USER", sessionUser);
					User user = dao.selectById(userID);
					userRole = user.getUserRole();
					if(userRole == 4){
						/*/recruitment-system/WebContent/system_administrator/html/main.html*/
						response.sendRedirect("system_administrator/html/main.html");
					}else{
						out.print("<script type='text/javascript'>");
						out.print("alert('�����ǹ���Ա����ͨ���û���¼������¼!');");
						out.print("</script>");
						response.sendRedirect("login.jsp");
					}
				} else {
					// �û���¼��Ϣ���󣬽��д�����ʾ
					out.print("<script type='text/javascript'>");
					out.print("alert('�û���������������������룡');");
					out.print("window.location = 'system_administrator/administrator_login.jsp'");
					out.print("</script>");
				}
			}
		}
	}
}
