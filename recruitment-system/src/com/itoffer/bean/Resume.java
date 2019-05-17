package com.itoffer.bean;
import java.util.Date;

import com.itoffer.dao.ResumeDAO;

public class Resume {
	// ������ʶ
	private int basicinfoId;
	// �û�id
	private int userId;
	// ���� 
	private String realName;
	// �Ա�
	private String gender;
	// ��������
	private Date birthday;
	// ��ǰ���ڵ�
	private String currentLoca;
	// �������ڵ�
	private String residentLoca;
	// �ֻ�
	private String telephone;
	// ����
	private String email;
	// ��ְ����
	private String jobIntension;
	// ��������
	private String jobExperience;
	// ͷ��
	private String headShot;
	// ������Ϣ���½��
	private String resumeUpdateResult;
//========================================================================
	//================��������================
	private String graduateSchool;
	//�Ͷ�ʱ��
	private Date studyStartTime;
	//ѧ��
	private String education;
	//רҵ
	private String major;
	//================��Ŀ����================
	//��Ŀ����
	private String entryName;
	//���뿪ʼʱ��
	private Date entryStartTime;
	//�������ʱ��
	private Date entryStopTime;
	//����ְλ
	private String assumeOffice;
	//================��������================
	//��������
	private String resumeEnclosure;
	//================����������================
	//����������
	private String completionOfResume;
	
	public Resume() {
		super();
	}

	public Resume(String realName, String gender, Date birthday,
			String currentLoca, String residentLoca, String telephone,
			String email, String jobIntension, String jobExperience) {
		super();
		this.realName = realName;
		this.gender = gender;
		this.birthday = birthday;
		this.currentLoca = currentLoca;
		this.residentLoca = residentLoca;
		this.telephone = telephone;
		this.email = email;
		this.jobIntension = jobIntension;
		this.jobExperience = jobExperience;
	}

	/**
	 * ȫ������
	 * @param basicinfoId
	 * @param userId
	 * @param realName
	 * @param gender
	 * @param birthday
	 * @param currentLoca
	 * @param residentLoca
	 * @param telephone
	 * @param email
	 * @param jobIntension
	 * @param jobExperience
	 * @param headShot
	 * @param resumeUpdateResult
	 * @param graduateSchool
	 * @param studyTime
	 * @param education
	 * @param major
	 * @param entryName
	 * @param entryStartTime
	 * @param entryStopTime
	 * @param assumeOffice
	 * @param resumeEnclosure
	 */
	public Resume(int basicinfoId, int userId, String realName, String gender, Date birthday,
			String currentLoca, String residentLoca, String telephone, String email, String jobIntension,
			String jobExperience, String headShot, String resumeUpdateResult, String graduateSchool,
			Date studyTime, String education, String major, String entryName, Date entryStartTime, Date entryStopTime,
			String assumeOffice, String resumeEnclosure,String completionOfResume) {
		super();
		this.basicinfoId = basicinfoId;
		this.userId = userId;
		this.realName = realName;
		this.gender = gender;
		this.birthday = birthday;
		this.currentLoca = currentLoca;
		this.residentLoca = residentLoca;
		this.telephone = telephone;
		this.email = email;
		this.jobIntension = jobIntension;
		this.jobExperience = jobExperience;
		this.headShot = headShot;
		this.resumeUpdateResult = resumeUpdateResult;
		this.birthday = birthday;
		this.graduateSchool = graduateSchool;
		this.studyStartTime = studyTime;
		this.education = education;
		this.major = major;
		this.entryName = entryName;
		this.entryStartTime = entryStartTime;
		this.entryStopTime = entryStopTime;
		this.assumeOffice = assumeOffice;
		this.resumeEnclosure = resumeEnclosure;
		this.completionOfResume = completionOfResume;
	}
	
	/**
	 * ��װ������Ϣ
	 * @param userId
	 * @param realName
	 * @param gender
	 * @param birthday
	 * @param currentLoca
	 * @param residentLoca
	 * @param telephone
	 * @param email
	 * @param jobIntension
	 * @param jobExperience
	 * @param headShot
	 * @param graduateSchool
	 * @param studyStartTime
	 * @param education
	 * @param major
	 * @param entryName
	 * @param entryStartTime
	 * @param entryStopTime
	 * @param assumeOffice
	 * @param resumeEnclosure
	 */
	public Resume(int userId, String realName, String gender, Date birthday, String currentLoca, String residentLoca,
			String telephone, String email, String jobIntension, String jobExperience, String headShot,
			String graduateSchool, Date studyStartTime, String education, String major, String entryName,
			Date entryStartTime, Date entryStopTime, String assumeOffice, String resumeEnclosure) {
		super();
		this.userId = userId;
		this.realName = realName;
		this.gender = gender;
		this.birthday = birthday;
		this.currentLoca = currentLoca;
		this.residentLoca = residentLoca;
		this.telephone = telephone;
		this.email = email;
		this.jobIntension = jobIntension;
		this.jobExperience = jobExperience;
		this.headShot = headShot;
		this.graduateSchool = graduateSchool;
		this.studyStartTime = studyStartTime;
		this.education = education;
		this.major = major;
		this.entryName = entryName;
		this.entryStartTime = entryStartTime;
		this.entryStopTime = entryStopTime;
		this.assumeOffice = assumeOffice;
		this.resumeEnclosure = resumeEnclosure;
	}

	public void setResumeUpdate(Resume resumeBasicinfo){
		// ���¼���������Ϣ
		try{
			ResumeDAO dao = new ResumeDAO();
			dao.update(resumeBasicinfo);
		}catch(Exception e){
			resumeUpdateResult="����ʧ�ܣ�";
		}
		resumeUpdateResult="���³ɹ���";
	}
	
	public String getResumeUpdateResult(){
		return resumeUpdateResult;
	}
	
	public void setbirthday(Date birthday){
		this.birthday = birthday;
	}
	public Date getbirthday(){
		return birthday;
	}
	public int getBasicinfoId() {
		return basicinfoId;
	}
	public void setBasicinfoId(int basicinfoId) {
		this.basicinfoId = basicinfoId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCurrentLoca() {
		return currentLoca;
	}
	public void setCurrentLoca(String currentLoca) {
		this.currentLoca = currentLoca;
	}
	public String getResidentLoca() {
		return residentLoca;
	}
	public void setResidentLoca(String residentLoca) {
		this.residentLoca = residentLoca;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJobIntension() {
		return jobIntension;
	}
	public void setJobIntension(String jobIntension) {
		this.jobIntension = jobIntension;
	}
	public String getJobExperience() {
		return jobExperience;
	}
	public void setJobExperience(String jobExperience) {
		this.jobExperience = jobExperience;
	}

	public String getHeadShot() {
		return headShot;
	}

	public void setHeadShot(String headShot) {
		this.headShot = headShot;
	}

	//================================================================
	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public Date getStudyStartTime() {
		return studyStartTime;
	}

	public void setStudyStartTime(Date studyStartTime) {
		this.studyStartTime = studyStartTime;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}

	public Date getEntryStartTime() {
		return entryStartTime;
	}

	public void setEntryStartTime(Date entryStartTime) {
		this.entryStartTime = entryStartTime;
	}

	public Date getEntryStopTime() {
		return entryStopTime;
	}

	public void setEntryStopTime(Date entryStopTime) {
		this.entryStopTime = entryStopTime;
	}

	public String getAssumeOffice() {
		return assumeOffice;
	}

	public void setAssumeOffice(String assumeOffice) {
		this.assumeOffice = assumeOffice;
	}

	public String getResumeEnclosure() {
		return resumeEnclosure;
	}

	public void setResumeEnclosure(String resumeEnclosure) {
		this.resumeEnclosure = resumeEnclosure;
	}

	public void setResumeUpdateResult(String resumeUpdateResult) {
		this.resumeUpdateResult = resumeUpdateResult;
	}

	public String getCompletionOfResume() {
		return completionOfResume;
	}

	public void setCompletionOfResume(String completionOfResume) {
		this.completionOfResume = completionOfResume;
	}
}
