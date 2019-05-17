package com.itoffer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itoffer.bean.User;
import com.itoffer.util.DBUtil;

public class UserDAO {
	public void saveRealOrLoginName(String userLogname,String userRealname,int userId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		//ע���û�
		String sql = "UPDATE tb_user SET user_logname=?, user_realname=? WHERE user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userLogname);
			pstmt.setString(2, userRealname);
			pstmt.setInt(3, userId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}
	
	/**
	 * ��֤Email�Ƿ��ѱ�ע��
	 * 
	 * @return
	 */
	public boolean isExistEmail(String email) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM tb_user WHERE user_email=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return false;
	}
	
	/**
	 * �û�ע�ᣨ��ͨ�û���
	 * 
	 * @param email
	 * @param password
	 */
	public void save(User user) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		//ע���û�
		String sql = "INSERT INTO tb_user(user_id, user_email, user_pwd, user_logname, user_realname, user_role, user_state, user_registdate) VALUES(ITOFFER_USER.NEXTVAL,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserEmail());
			pstmt.setString(2, user.getUserPwd());
			pstmt.setString(3, user.getUserLogname());
			pstmt.setString(4, user.getUserRealname());
			pstmt.setInt(5, user.getUserRole());
			pstmt.setInt(6, 1);
			pstmt.setTimestamp(7, new Timestamp(new Date().getTime()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}
	
	/**
	 * �û���Ϣ�޸ģ�����Ա��
	 * 
	 * @param email
	 * @param password
	 */
	public void update(User user) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		//ע���û�
		String sql = "UPDATE tb_user SET user_email=?, user_logname=?, user_realname=?, user_role=?, user_state=? WHERE user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserEmail());
			pstmt.setString(2, user.getUserLogname());
			pstmt.setString(3, user.getUserRealname());
			pstmt.setInt(4, user.getUserRole());
			pstmt.setInt(5, user.getUserState());
			pstmt.setInt(6, user.getUserId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}
	
	/**
	 * �û������޸�
	 * @param user
	 */
	public void updatePwd(User user) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		//ע���û�
		String sql = "UPDATE tb_user SET user_pwd=? WHERE user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserPwd());
			pstmt.setInt(2, user.getUserId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}
	
	/**
	 * �û���Ϣɾ��������Ա��
	 * 
	 * @param email
	 * @param password
	 */
	public void delete(int userId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		
		//ע���û�
		String sql = "DELETE FROM tb_user WHERE user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}
	
	
	/**
	 * ע���û���¼
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public User login(String email, String password) {
		User user = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM tb_user WHERE user_email=? and user_pwd=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next())
				user = new User(rs.getInt("user_id"),rs.getString("user_email"),rs.getString("user_pwd"),rs.getInt("user_role"),rs.getInt("user_state"),rs.getDate("user_registdate"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return user;
	}
	
	/**
	 * ����Ա�û���¼
	 * @param managerLoginName
	 * @param password
	 * @return
	 */
	public User managerLogin(String userEmail, String password) {
		// TODO Auto-generated method stub
		User user = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM tb_user WHERE user_email=? and user_pwd=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userEmail);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next())
				user = new User(rs.getInt("user_id"),rs.getString("user_email"),rs.getString("user_pwd"),rs.getString("user_logname"),rs.getInt("user_role"),rs.getInt("user_state"),rs.getDate("user_registdate"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return user;
	}
	
	/**
	 * �����û�id�����û�
	 * @param uid
	 * @return
	 */
	public User selectById(int uid){
		User user=new User();
		Connection connection = DBUtil.getConnection() ;
		if (connection == null)
			return null ;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		try {
			pstmt=connection.prepareStatement("SELECT user_id,user_email,user_logname ,"
					+ "user_realname,user_role,user_state FROM tb_user WHERE user_id=?"); 
			pstmt.setInt(1, uid);
			rs = pstmt.executeQuery();
			while (rs.next()){
				user.setUserId(rs.getInt("user_id"));
				user.setUserEmail(rs.getString("user_email"));
				user.setUserLogname(rs.getString("user_logname"));
				user.setUserRealname(rs.getString("user_realname"));
				user.setUserRole(rs.getInt("user_role"));
				user.setUserState(rs.getInt("user_state"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, connection);
		}
		return user;
	}
	
	/**
	 * �����û�id�����û�����
	 * @param uid
	 * @return
	 */
	public User selectPasswordById(int uid) {
		User user=new User();
		Connection connection = DBUtil . getConnection() ;
		if (connection == null)
			return null ;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		try {
			pstmt=connection.prepareStatement("SELECT user_pwd WHERE user_id=?"); 
			pstmt.setInt(1, uid);
			rs = pstmt.executeQuery();
			if (rs.next())
				user.setUserPwd(rs.getString("user_pwd"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, connection);
		}
		return user;
	}

	//��ѯ�����ҳ���ܼ�¼��
	public int getRecordCount() {
		int recordCount = 0;
		Connection conn = DBUtil .getConnection() ;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT count(*) FROM tb_user";
			pstmt = conn.prepareStatement(sql) ;
			rs = pstmt.executeQuery() ;
			if (rs.next())
				recordCount = rs.getInt(1) ;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
				DBUtil.closeJDBC(rs, pstmt, conn) ;
		}
		return recordCount;
	}

	//���屾ҳ��¼����ֵ
	public List<User> getUserPageList(int pageNo, int pageSize) {
		int firstIndex = pageSize * (pageNo-1 ) ;
		List<User> list = new ArrayList<User> () ;
		Connection connection = DBUtil . getConnection() ;
		if (connection == null)
			return null ;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		try {
			pstmt=connection.prepareStatement(
					"SELECT * FROM (SELECT a.*, ROWNUM rn FROM "
					+ "(SELECT user_id,user_email,user_logname ,user_realname,user_role,"
					+ "user_state ,user_registdate FROM tb_user) a WHERE ROWNUM<=? )WHERE rn>?"); 
			pstmt.setInt(1, firstIndex+pageSize);
			pstmt.setInt(2, firstIndex);
			rs = pstmt.executeQuery();
			while (rs.next()){
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserEmail(rs.getString("user_email"));
				user.setUserLogname(rs.getString("user_logname"));
				user.setUserRealname(rs.getString("user_realname"));
				user.setUserRole(rs.getInt("user_role"));
				user.setUserState(rs.getInt("user_state"));
				user.setUserRegistDate(rs.getDate("user_registdate"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, connection);
		}
		return list;
	}

	/**
	 * �ж��Ƿ����м���
	 * 
	 * @param email
	 * @return
	 */
	public int isExistResume(int userID) {
		int infoID = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT basicinfo_id FROM tb_resume_basicinfo WHERE user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next())
				infoID = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return infoID;
	}

	/**
	 * �ж��Ƿ�������ҵ��Ϣ
	 * 
	 * @param email
	 * @return
	 */
	public int isExistEnterpriseInfo(int userID) {
		int infoID = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT basicinfo_id FROM tb_resume_basicinfo WHERE user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next())
				infoID = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return infoID;
	}
	
	/**
	 * �ж��Ƿ�������ͷ��Ϣ
	 * 
	 * @param email
	 * @return
	 */
	public int isExistHeadhuntingInfo(int userID) {
		int infoID = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT basicinfo_id FROM tb_resume_basicinfo WHERE user_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next())
				infoID = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return infoID;
	}
	



	
	
}
