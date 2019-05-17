package com.itoffer.bean;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ��ҵ��Ϣʵ����
 * 
 * @author TianYanan
 *
 */
public class Company {
	// ��ҵ��ʶ
	private int companyId;
	//��ҵhr
	private int companyHrId;
	// ��ҵ����
	private String companyName;
	//ע��ʱ��
	private Date companyRegisterTime;
	//ע���ʱ�����Ԫ��
	private int companyRegisterCapital;
	//���˴���
	private String companyLegalPerson;
	//ͳһ���ô���
	private String companyCreditCode;
	// ��ҵ���ڵ���
	private String companyArea;
	// ��ҵ��ģ
	private String companySize;
	// ��ҵ����
	private String companyType;
	// ��ҵ
	private String companyIndustry;
	// ��ҵ���
	private String companyBrief;
	// ��Ƹ״̬:1��Ƹ�� ����ͣ 3�ѽ���
	private int companyState;
	// �������
	private int companySort;
	// �����
	private int companyViewnum;
	// ����ͼƬ
	private String companyPic;
	//��ϵ�绰
	private String companyTel;
	// ְλ
	private Set<Job> jobs = new HashSet<Job>();

	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	//ȫ��
	public Company(int companyId, int companyHrId, String companyName, Date companyRegisterTime,
			int companyRegisterCapital, String companyLegalPerson, String companyCreditCode, String companyArea,
			String companySize, String companyType, String companyIndustry, String companyBrief, int companyState,
			int companySort, int companyViewnum, String companyPic, String companyTel) {
		super();
		this.companyId = companyId;
		this.companyHrId = companyHrId;
		this.companyName = companyName;
		this.companyRegisterTime = companyRegisterTime;
		this.companyRegisterCapital = companyRegisterCapital;
		this.companyLegalPerson = companyLegalPerson;
		this.companyCreditCode = companyCreditCode;
		this.companyArea = companyArea;
		this.companySize = companySize;
		this.companyType = companyType;
		this.companyIndustry = companyIndustry;
		this.companyBrief = companyBrief;
		this.companyState = companyState;
		this.companySort = companySort;
		this.companyViewnum = companyViewnum;
		this.companyPic = companyPic;
		this.companyTel = companyTel;
	}

	//��ҵ�޸ģ�����Ա��
	public Company(int companyId, int companyHrId, String companyName,int companyRegisterCapital, 
			String companyLegalPerson, String companyCreditCode, String companyArea,String companySize, 
			String companyType, String companyIndustry, String companyBrief, int companyState, String companyTel) {
		super();
		this.companyId = companyId;
		this.companyHrId = companyHrId;
		this.companyName = companyName;
		this.companyRegisterCapital = companyRegisterCapital;
		this.companyLegalPerson = companyLegalPerson;
		this.companyCreditCode = companyCreditCode;
		this.companyArea = companyArea;
		this.companySize = companySize;
		this.companyType = companyType;
		this.companyIndustry = companyIndustry;
		this.companyBrief = companyBrief;
		this.companyState = companyState;
		this.companyTel = companyTel;
	}
	
	//�����ҵ��hr��
	public Company(String companyName, Date companyRegisterTime, int companyRegisterCapital,
			String companyLegalPerson, String companyCreditCode, String companyArea, String companySize,
			String companyType, String companyIndustry, String companyBrief, int companyState, int companySort,
			int companyViewnum, String companyPic, String companyTel) {
		super();
		this.companyName = companyName;
		this.companyRegisterTime = companyRegisterTime;
		this.companyRegisterCapital = companyRegisterCapital;
		this.companyLegalPerson = companyLegalPerson;
		this.companyCreditCode = companyCreditCode;
		this.companyArea = companyArea;
		this.companySize = companySize;
		this.companyType = companyType;
		this.companyIndustry = companyIndustry;
		this.companyBrief = companyBrief;
		this.companyState = companyState;
		this.companySort = companySort;
		this.companyViewnum = companyViewnum;
		this.companyPic = companyPic;
		this.companyTel = companyTel;
	}
	
	//�����ҵ����ͷ������ҵ��
		public Company(String companyName, Date companyRegisterTime, int companyRegisterCapital,
				String companyLegalPerson, String companyCreditCode, String companyArea, String companySize,
				String companyType, String companyIndustry, String companyBrief, int companyState, int companySort,
				int companyViewnum, String companyTel) {
			super();
			this.companyName = companyName;
			this.companyRegisterTime = companyRegisterTime;
			this.companyRegisterCapital = companyRegisterCapital;
			this.companyLegalPerson = companyLegalPerson;
			this.companyCreditCode = companyCreditCode;
			this.companyArea = companyArea;
			this.companySize = companySize;
			this.companyType = companyType;
			this.companyIndustry = companyIndustry;
			this.companyBrief = companyBrief;
			this.companyState = companyState;
			this.companySort = companySort;
			this.companyViewnum = companyViewnum;
			this.companyTel = companyTel;
		}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getCompanyHrId() {
		return companyHrId;
	}

	public void setCompanyHrId(int companyHrId) {
		this.companyHrId = companyHrId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getCompanyRegisterTime() {
		return companyRegisterTime;
	}

	public void setCompanyRegisterTime(Date companyRegisterTime) {
		this.companyRegisterTime = companyRegisterTime;
	}

	public int getCompanyRegisterCapital() {
		return companyRegisterCapital;
	}

	public void setCompanyRegisterCapital(int companyRegisterCapital) {
		this.companyRegisterCapital = companyRegisterCapital;
	}

	public String getCompanyLegalPerson() {
		return companyLegalPerson;
	}

	public void setCompanyLegalPerson(String companyLegalPerson) {
		this.companyLegalPerson = companyLegalPerson;
	}

	public String getCompanyCreditCode() {
		return companyCreditCode;
	}

	public void setCompanyCreditCode(String companyCreditCode) {
		this.companyCreditCode = companyCreditCode;
	}

	public String getCompanyArea() {
		return companyArea;
	}

	public void setCompanyArea(String companyArea) {
		this.companyArea = companyArea;
	}

	public String getCompanySize() {
		return companySize;
	}

	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyIndustry() {
		return companyIndustry;
	}

	public void setCompanyIndustry(String companyIndustry) {
		this.companyIndustry = companyIndustry;
	}

	public String getCompanyBrief() {
		return companyBrief;
	}

	public void setCompanyBrief(String companyBrief) {
		this.companyBrief = companyBrief;
	}

	public int getCompanyState() {
		return companyState;
	}

	public void setCompanyState(int companyState) {
		this.companyState = companyState;
	}

	public int getCompanySort() {
		return companySort;
	}

	public void setCompanySort(int companySort) {
		this.companySort = companySort;
	}

	public int getCompanyViewnum() {
		return companyViewnum;
	}

	public void setCompanyViewnum(int companyViewnum) {
		this.companyViewnum = companyViewnum;
	}

	public String getCompanyPic() {
		return companyPic;
	}

	public void setCompanyPic(String companyPic) {
		this.companyPic = companyPic;
	}

	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

}
