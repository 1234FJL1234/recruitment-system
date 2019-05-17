package com.itoffer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.itoffer.bean.Resume;
import com.itoffer.util.DBUtil;

public class ResumeDAO {
	/**
	 * ����������Ϣ���
	 * @param basicinfo
	 * @param UserID
	 * @return
	 */
	public int addBaseInfo(Resume basicinfo, int UserID) {
		int basicinfoID = 0;
		/*String sql = "INSERT INTO tb_resume_basicinfo("
				+ "basicinfo_id, realname, gender, birthday, current_Loca, "
				+ "resident_Loca, telephone, email, job_intension, job_experience, head_shot,User_id) "
				+ "VALUES(ITOFFER_RESUMEBASICINFO.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";*/
		String sql = "INSERT INTO tb_resume_basicinfo("
				+ "basicinfo_id, realname, gender, birthday, current_Loca, "
				+ "resident_Loca, telephone, email, job_intension, job_experience, "
				+ "head_shot ,completion_of_resume , User_id)"
				+ "VALUES(ITOFFER_RESUMEBASICINFO.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			// �ر��Զ��ύ
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, basicinfo.getRealName());
			pstmt.setString(2, basicinfo.getGender());
			pstmt.setTimestamp(3, basicinfo.getBirthday() == null ? null
					: new Timestamp(basicinfo.getBirthday().getTime()));
			pstmt.setString(4, basicinfo.getCurrentLoca());
			pstmt.setString(5, basicinfo.getResidentLoca());
			pstmt.setString(6, basicinfo.getTelephone());
			pstmt.setString(7, basicinfo.getEmail());
			pstmt.setString(8, basicinfo.getJobIntension());
			pstmt.setString(9, basicinfo.getJobExperience());
			pstmt.setString(10, basicinfo.getHeadShot());
			pstmt.setString(11, "11");//��������Ϣ�Ľ�����������Ϊ1
			pstmt.setInt(12, UserID);
			pstmt.executeUpdate();
			pstmt.close();
			// ��ȡ��ǰ���ɵļ�����ʶ
			String sql2 = "SELECT ITOFFER_RESUMEBASICINFO.CURRVAL FROM dual";
			pstmt = conn.prepareStatement(sql2);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				basicinfoID = rs.getInt(1);
			// �����ύ
			conn.commit();
		} catch (SQLException e) {
			try {
				// ����ع�
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
		return basicinfoID;
	}
	/**
	 * ������������
	 * @param basicinfo
	 * @param basicinfoID
	 */
	public void addEducationExperience(Resume basicinfo, int basicinfoId) {
		String sql = "UPDATE tb_resume_basicinfo SET graduate_school=?, "
				+ "study_start_time=?, education=?, major=?, completion_of_resume=? WHERE basicinfo_id=?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, basicinfo.getGraduateSchool());
			pstmt.setTimestamp(2, basicinfo.getStudyStartTime() == null ? null
					: new Timestamp(basicinfo.getStudyStartTime().getTime()));
			pstmt.setString(3, basicinfo.getEducation());
			pstmt.setString(4, basicinfo.getMajor());
			pstmt.setString(5, "31");
			pstmt.setInt(6, basicinfoId);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}
	/**
	 * ������Ŀ����
	 * @param basicinfo
	 * @param basicinfoID
	 */
	public void addProjectExperience(Resume basicinfo, int basicinfoId) {
		String sql = "UPDATE tb_resume_basicinfo SET entry_name=?, entry_start_time=?,"
				+ " entry_stop_time=?, assume_office=?, completion_of_resume=? WHERE basicinfo_id=?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, basicinfo.getEntryName());
			pstmt.setTimestamp(2, basicinfo.getEntryStartTime() == null ? null
					: new Timestamp(basicinfo.getEntryStartTime().getTime()));
			pstmt.setTimestamp(3, basicinfo.getEntryStopTime() == null ? null
					: new Timestamp(basicinfo.getEntryStopTime().getTime()));
			pstmt.setString(4, basicinfo.getAssumeOffice());
			pstmt.setString(5, "41");
			/*pstmt.setString(19, basicinfo.getResumeEnclosure());*/
			pstmt.setInt(6, basicinfoId);
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}
	
	/**
	 * ��Ӽ���
	 * @param basicinfo
	 * @param basicinfoId
	 */
	public void addResumeEnclosure(int basicinfoId, String resumeEnclosureName) {
		String sql = "UPDATE tb_resume_basicinfo SET resume_enclosure=? ,completion_of_resume=? WHERE basicinfo_id=?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, resumeEnclosureName);
			pstmt.setString(2, "51");
			pstmt.setInt(6, basicinfoId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}

	/**
	 * ������Ƭ����
	 * 
	 * @param basicinfoId
	 * @param newFileName
	 */
	public void updateHeadShot(int basicinfoId, String newFileName) {
		String sql = "UPDATE tb_resume_basicinfo SET head_shot=? ,completion_of_resume=?  WHERE basicinfo_id=?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newFileName);
			pstmt.setString(2, "21");
			pstmt.setInt(3, basicinfoId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}
	
	/**
	 * ���ݼ�����ʶ��ѯ����������Ϣ(ǰ̨)
	 * @param UserID
	 * @return
	 */
	public Resume selectBasicinfoByID(int userID){
		Resume resume = new Resume();
		String sql = "SELECT * FROM tb_resume_basicinfo WHERE User_id=?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userID);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				resume.setBasicinfoId(rs.getInt("basicinfo_id"));
				resume.setUserId(rs.getInt("user_id"));
				resume.setRealName(rs.getString("realname"));
				resume.setGender(rs.getString("gender"));
				resume.setBirthday(rs.getDate("birthday"));
				resume.setCurrentLoca(rs.getString("current_loca"));
				resume.setResidentLoca(rs.getString("resident_loca"));
				resume.setTelephone(rs.getString("telephone"));
				resume.setEmail(rs.getString("email"));
				resume.setJobExperience(rs.getString("job_experience"));
				resume.setJobIntension(rs.getString("job_intension"));
				resume.setHeadShot(rs.getString("head_shot"));
				resume.setGraduateSchool(rs.getString("graduate_school"));
				resume.setStudyStartTime(rs.getDate("study_start_time"));
				resume.setEducation(rs.getString("education"));
				resume.setMajor(rs.getString("major"));
				resume.setEntryName(rs.getString("entry_name"));
				resume.setEntryStartTime(rs.getDate("entry_start_time"));
				resume.setEntryStopTime(rs.getDate("entry_stop_time"));
				resume.setAssumeOffice(rs.getString("assume_office"));
				resume.setResumeEnclosure(rs.getString("resume_enclosure"));
				resume.setCompletionOfResume(rs.getString("completion_of_resume"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
		return resume;
	}
	
	/**
	 * ���ݼ�����ʶ��ѯ����������Ϣ(ǰ̨)
	 * @param UserID
	 * @return
	 */
	public Resume selectBasicinfoByResumeID(int resumeID){
		Resume resume = new Resume();
		String sql = "SELECT * FROM tb_resume_basicinfo WHERE basicinfo_id=?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, resumeID);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				resume.setBasicinfoId(rs.getInt("basicinfo_id"));
				resume.setUserId(rs.getInt("user_id"));
				resume.setRealName(rs.getString("realname"));
				resume.setGender(rs.getString("gender"));
				resume.setBirthday(rs.getDate("birthday"));
				resume.setCurrentLoca(rs.getString("current_loca"));
				resume.setResidentLoca(rs.getString("resident_loca"));
				resume.setTelephone(rs.getString("telephone"));
				resume.setEmail(rs.getString("email"));
				resume.setJobExperience(rs.getString("job_experience"));
				resume.setJobIntension(rs.getString("job_intension"));
				resume.setHeadShot(rs.getString("head_shot"));
				resume.setGraduateSchool(rs.getString("graduate_school"));
				resume.setStudyStartTime(rs.getDate("study_start_time"));
				resume.setEducation(rs.getString("education"));
				resume.setMajor(rs.getString("major"));
				resume.setEntryName(rs.getString("entry_name"));
				resume.setEntryStartTime(rs.getDate("entry_start_time"));
				resume.setEntryStopTime(rs.getDate("entry_stop_time"));
				resume.setAssumeOffice(rs.getString("assume_office"));
				resume.setResumeEnclosure(rs.getString("resume_enclosure"));
				resume.setCompletionOfResume(rs.getString("completion_of_resume"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
		return resume;
	}
	
	/**
	 * ���¼���������Ϣ
	 * @param basicinfo
	 */
	public void update(Resume basicinfo) {
		String sql = "UPDATE tb_resume_basicinfo "
				+ "SET realname=?, gender=?,birthday=?,current_Loca=?,resident_Loca=?,"
				+ "telephone=?,email=?,job_intension=?,job_experience=? "
				+ "WHERE basicinfo_id=?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, basicinfo.getRealName());
			pstmt.setString(2, basicinfo.getGender());
			pstmt.setTimestamp(3, basicinfo.getBirthday() == null ? null
					: new Timestamp(basicinfo.getBirthday().getTime()));
			pstmt.setString(4, basicinfo.getCurrentLoca());
			pstmt.setString(5, basicinfo.getResidentLoca());
			pstmt.setString(6, basicinfo.getTelephone());
			pstmt.setString(7, basicinfo.getEmail());
			pstmt.setString(8, basicinfo.getJobIntension());
			pstmt.setString(9, basicinfo.getJobExperience());
			pstmt.setInt(10, basicinfo.getBasicinfoId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}
	
	//���屾ҳ��¼����ֵ
	public List<Resume> getResumePageList(int pageNo, int pageSize) {
		int firstIndex = pageSize * (pageNo-1 ) ;
		List<Resume> list = new ArrayList<Resume> () ;
		Connection connection = DBUtil.getConnection() ;
		if (connection == null)
			return null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt=connection.prepareStatement(
					"SELECT * FROM (SELECT a.*, ROWNUM rn FROM "
					+ "(SELECT basicinfo_id,user_id,realname,telephone,email, job_intension, job_experience"
					+ " FROM tb_resume_basicinfo) a WHERE ROWNUM<=? )WHERE rn>?"); 
			pstmt.setInt(1, firstIndex+pageSize);
			pstmt.setInt(2, firstIndex);
			rs = pstmt.executeQuery();
			while (rs.next()){
				Resume resumeinfo=new Resume();
				resumeinfo.setBasicinfoId(rs.getInt("basicinfo_id"));
				int userId=rs.getInt("user_id");
				resumeinfo.setRealName(rs.getString("realname")+"("+userId+")");
				resumeinfo.setTelephone(rs.getString("telephone"));
				resumeinfo.setEmail(rs.getString("email"));
				resumeinfo.setJobIntension(rs.getString("job_intension"));
				resumeinfo.setJobExperience(rs.getString("job_experience"));
				list.add(resumeinfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, connection);
		}
		return list;
	}
	
	//��ѯ�����ҳ���ܼ�¼��
	public int getRecordCount() {
		int recordCount = 0;
		Connection conn = DBUtil .getConnection() ;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT count(*) FROM tb_resume_basicinfo";
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
	
	/**
	 * ���ݼ�����ʶ��ѯ����������Ϣ(��̨����Ա)
	 * @param basicinfoId
	 * @return
	 */
	public Resume selectListinfoByID(int basicinfoId){
		Resume resume = new Resume();
		String sql = "SELECT * FROM tb_resume_basicinfo WHERE basicinfo_id=?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, basicinfoId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				resume.setBasicinfoId(rs.getInt("basicinfo_id"));
				resume.setUserId(rs.getInt("user_id"));
				resume.setRealName(rs.getString("realname"));
				resume.setGender(rs.getString("gender"));
				resume.setBirthday(rs.getDate("birthday"));
				resume.setCurrentLoca(rs.getString("current_Loca"));
				resume.setResidentLoca(rs.getString("resident_Loca"));
				resume.setTelephone(rs.getString("telephone"));
				resume.setEmail(rs.getString("email"));
				resume.setJobExperience(rs.getString("job_experience"));
				resume.setJobIntension(rs.getString("job_intension"));
				resume.setHeadShot(rs.getString("head_shot"));
				resume.setGraduateSchool(rs.getString("graduate_school"));
				resume.setStudyStartTime(rs.getDate("study_start_time"));
				resume.setEducation(rs.getString("education"));
				resume.setMajor(rs.getString("major"));
				resume.setEntryName(rs.getString("entry_name"));
				resume.setEntryStartTime(rs.getDate("entry_start_time"));
				resume.setEntryStopTime(rs.getDate("entry_stop_time"));
				resume.setAssumeOffice(rs.getString("assume_office"));
				resume.setResumeEnclosure(rs.getString("resume_enclosure"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
		return resume;
	}

	/**
	 * �û���Ϣɾ��������Ա��
	 * 
	 */
	public void delete(int basicinfoId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		
		//ע���û�
		String sql = "DELETE FROM tb_resume_basicinfo WHERE basicinfo_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, basicinfoId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}
}
