package com.itoffer.listener;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.itoffer.bean.User;
import com.itoffer.dao.UserDAO;

import sun.usagetracker.UsageTrackerClient;

/**
 * Application Lifecycle Listener implementation class OnlineUserListener
 * �����û�������
 *
 */
@WebListener
public class OnlineUserListener implements HttpSessionAttributeListener {
    /**
     * Default constructor. 
     */
    public OnlineUserListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    @SuppressWarnings("unchecked")
	public void attributeAdded(HttpSessionBindingEvent event){
    	// ��������������һ������ 
    	// TODO Auto-generated method stub
    	// �жϼ��������Ƿ�Ϊ��¼�û���Ϣ�ĻỰ������
    	if("SESSION_USER".equals(event.getName())){
    		/*//ʹsession��Ч
	    	LockHelper.putSession(event.getSession());*/
	    	// ��ȡ�Ự������ֵ:��ǰ��¼���û�����
	    	User user = (User) event.getValue();
	    	UserDAO userDAO = new UserDAO();
	    	User userOnline = userDAO.selectById(user.getUserId());
	    	//��ȡӦ�������Ķ���
	    	ServletContext application = event.getSession().getServletContext();
	    	//��ȡ�����Ӧ���������е������û���Ϣ�б�
	    	// ����sessionID��user��ӳ��  
	    	Map<Integer,User> onlineUserMap = (Map<Integer,User>)application.getAttribute("ONLINE_USER");
	    	if (onlineUserMap == null)
		    	onlineUserMap = new Hashtable<Integer,User>();
		    // ����ǰ��¼���û��������Ӧ��������:�����û���Ϣ�б�
		    onlineUserMap.put(user.getUserId(),userOnline);
		    application.setAttribute("ONLINE_USER", onlineUserMap);
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    	/*// �жϼ��������Ƿ�Ϊ��¼�û���Ϣ�ĻỰ������
    	if("SESSION_USER".equals(event.getName())){
	    	// ��ȡ�Ự������ֵ:��ǰ��¼���û�����
	    	User user = (User) event.getValue();
	    	//��ȡӦ�������Ķ���
	    	ServletContext application = event.getSession().getServletContext();
	    	//��ȡ�����Ӧ���������е������û���Ϣ�б�
	    	// ����sessionID��user��ӳ��  
	    	Map<Integer,User> onlineUserMap = (Map<Integer,User>)application.getAttribute("ONLINE_USER");
		    // ����ǰ��¼���û��������Ӧ��������:�����û���Ϣ�б�
		    onlineUserMap.remove(user.getUserId(),user);
    	}*/
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }
	
}
