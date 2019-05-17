package com.itoffer.listener;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.itoffer.bean.User;

/**
 * Application Lifecycle Listener implementation class LogoutUserListener
 * �����û��˳�������
 */
@WebListener
public class LogoutUserListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public LogoutUserListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent event)  { 
         // TODO Auto-generated method stub
    	/*//��ȡӦ�������Ķ���
		ServletContext application = event.getSession().getServletContext () ;
		//��ȡ�����Ӧ���������е������û���Ϣ�б�
		Map<Integer, User> onlineUserMap = (Map<Integer, User>) application.getAttribute("ONLINE_USER");
		//��ȡ�Ự������ֵ:��ǰ��¼���û�����
		User user = (User) event.getSession().getAttribute("SESSION_USER");
    	onlineUserMap.put(user.getUserId(), user);*/
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    @SuppressWarnings("unchecked")
	public void sessionDestroyed(HttpSessionEvent event) {
         // TODO Auto-generated method stub
    	HttpSession session = event.getSession();
    	// �жϼ��������Ƿ�Ϊ��¼�û���Ϣ�ĻỰ������
    	Enumeration<String> attrnames = event.getSession().getAttributeNames();
		while (attrnames.hasMoreElements()){
    		/*System.out.println(attrnames.nextElement() ) ;*/
    		if ("SESSION_USER".equals(attrnames.nextElement())) {
	    		//��ȡ�Ự������ֵ:��ǰ��¼���û�����
	    		User user = (User) event.getSession().getAttribute("SESSION_USER");
	    		//��ȡӦ�������Ķ���
	    		ServletContext application = event.getSession().getServletContext() ;
	    		//��ȡ�����Ӧ���������е������û���Ϣ�б�
	    		Map<Integer, User> onlineUserMap = (Map<Integer, User>) application.getAttribute("ONLINE_USER");
	    		if (onlineUserMap != null) {
		    		//����ǰ��¼���û������Ƴ�Ӧ��������:�����û���Ϣ�б�
		    		onlineUserMap.remove(user.getUserId());
		    		/*session.invalidate();*/
		    		application.setAttribute("ONLINE_USER",onlineUserMap) ;
	    		}
	    	}
	    }
    }
}
