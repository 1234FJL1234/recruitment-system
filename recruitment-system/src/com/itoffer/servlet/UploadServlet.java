package com.itoffer.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.itoffer.dao.ResumeDAO;

/**
 * ����ͷ��ͼƬ�ϴ�
 * 
 * @author TianYanan
 *
 */
@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// ��ȡ�����������
		String type = request.getParameter("type");
		// ����ͷ���ϴ�����
		if ("uploadResumeHeadShot".equals(type)) {
			// ��ȡ�ϴ��ļ���
			Part part = request.getPart("headShot");
			// ��ȡ�ϴ��ļ�����
			String fileName = part.getSubmittedFileName();
			// Ϊ��ֹ�ϴ��ļ����������ļ�����������
			String newFileName = System.currentTimeMillis() + fileName;
			// ���ϴ����ļ������ڷ�������Ŀ����·����applicant/imagesĿ¼��
			String filepath = getServletContext().getRealPath("/file/upload_headshot");
			System.out.println("ͷ�񱣴�·��Ϊ��" + filepath);
			File f = new File(filepath);
			if (!f.exists())
				f.mkdirs();
			part.write(filepath + "/" + newFileName);
			// �ӻỰ�����л�ȡ��ǰ�û�������ʶ
			int resumeID = Integer.parseInt(request.getParameter("basicinfoId"));
			// ���¼�����Ƭ
			ResumeDAO dao = new ResumeDAO();
			dao.updateHeadShot(resumeID, newFileName);
			// ��Ƭ���³ɹ����ص����ҵļ�����ҳ��
			response.sendRedirect("ResumeServlet?type=select");
		} else if ("uploadResumeEnclosure".equals(type)) {
			// ��ȡ�ϴ��ļ���
			Part part = request.getPart("uploadResumeFile");
			// ��ȡ�ϴ��ļ�����
			String fileName = part.getSubmittedFileName();
			// Ϊ��ֹ�ϴ��ļ����������ļ�����������
			String newFileName = System.currentTimeMillis() + fileName;
			// ���ϴ����ļ������ڷ�������Ŀ����·����/file/upload_resumeĿ¼��
			String filepath = getServletContext().getRealPath("/file/upload_resume");
			System.out.println("�����ļ�����·��Ϊ��" + filepath);
			File f = new File(filepath);
			if (!f.exists())
				f.mkdirs();
			part.write(filepath + "/" + newFileName);
			// �ӻỰ�����л�ȡ��ǰ�û�������ʶ
			int resumeID = Integer.parseInt(request.getParameter("basicinfoId"));
			// ���¼�����Ƭ
			ResumeDAO dao = new ResumeDAO();
			dao.addResumeEnclosure(resumeID, newFileName);
			response.sendRedirect("ResumeServlet?type=select");
		}
		
		
		
		
		
	}

}
