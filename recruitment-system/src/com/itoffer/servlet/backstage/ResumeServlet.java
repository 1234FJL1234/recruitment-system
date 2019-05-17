package com.itoffer.servlet.backstage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itoffer.bean.PageBean;
import com.itoffer.bean.Resume;
import com.itoffer.dao.ResumeDAO;
import com.itoffer.dao.UserDAO;

/**
 * ����������Ϣ����Servlet
 * 
 * @author TianYanan
 *
 */
@WebServlet("/ManagerResumeServlet")
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
		if("list".equals(type)){
			//��ȡ����ҳ��
			int pageNo = Integer.parseInt(request.getParameter("pageNo")==null?"1":request.getParameter ("pageNo"));
			ResumeDAO dao = new ResumeDAO() ;
			//��ѯ�ܼ�¼��
			int recordCount = dao.getRecordCount();
			//��ѯ����ҳ������
			List<Resume> list = dao.getResumePageList(pageNo, 5);
			
			//����ҳ��Ϣ��װ��PageBean������
			PageBean<Resume> pageBean = new PageBean<Resume>() ;
			pageBean.setPageNo(pageNo);
			pageBean.setRecordCount(recordCount);
			pageBean.setPageData(list);
			//����ҳ���ݶ���PageBean�������������������
			request.setAttribute("pageBean", pageBean) ;
			//������ת���������б�ҳ��
			request.getRequestDispatcher("system_administrator/jsp/resumeList.jsp").forward(request, response);
		} else if("viewOper".equals(type)){
			int basicinfoId = Integer.parseInt(request.getParameter("basicinfoId"));
			ResumeDAO dao = new ResumeDAO();
			Resume resume=dao.selectListinfoByID(basicinfoId);
			request.setAttribute("resume", resume);
			request.getRequestDispatcher("system_administrator/jsp/resumeView.jsp").forward(request, response);
		}else if("delectOper".equals(type)){
			int basicinfoId = Integer.parseInt(request.getParameter ("basicinfoId")) ;
			ResumeDAO dao = new ResumeDAO();
			dao.delete(basicinfoId);
			
			//��ȡ����ҳ��
			int pageNo = Integer.parseInt(request.getParameter("pageNo")==null?"1":request.getParameter ("pageNo"));
			//��ѯ�ܼ�¼��
			int recordCount = dao.getRecordCount();
			//��ѯ����ҳ������
			List<Resume> list = dao.getResumePageList(pageNo, 5);
			//����ҳ��Ϣ��װ��PageBean������
			PageBean<Resume> pageBean = new PageBean<Resume>() ;
			pageBean.setPageNo(pageNo);
			pageBean.setRecordCount(recordCount);
			pageBean.setPageData(list);
			// ����ѯ����ҵ�б����request������
			request.setAttribute("pageBean", pageBean);
			request.getRequestDispatcher("system_administrator/jsp/resumeList.jsp").forward(request, response);
		}
	}

}
