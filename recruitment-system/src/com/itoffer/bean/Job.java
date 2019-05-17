package com.itoffer.bean;

/**
 * ְλ��Ϣʵ����
 * 
 * @author TianYanan
 *
 */
public class Job {
	// ְλ���
	private int jobId;
	// ������ҵ
	private Company company;
//====ְλ����===============================================
	// ְλ����
	private String jobName;
	// ��Ƹ����
	private int jobHiringnum;
	// ְλн��
	private String jobSalary;
	// ��������
	private String jobArea;
//====��ְҪ��===============================================
	private String jobAgeRequirements;
	private String jobEducationRequirements;
	private String jobSexRequirements;
	private String jobForeignLanguages;
//====��λְ��===============================================
	private String jobDesc;
//====����===============================================	
	// ��Ƹ״̬:1��Ƹ�� 2����ͣ 3�ѽ���
	private int jobState;
	//�����
	private int jobViewNumber;
	//������
	private int jobApplicantsNumber;
	//ͨ������
	private int jobPassingNumber;

	public Job() {
		super();
	}

	public Job(int jobId, Company company, String jobName, int jobHiringnum,
			String jobSalary, String jobArea, String jobDesc, int jobState) {
		super();
		this.jobId = jobId;
		this.company = company;
		this.jobName = jobName;
		this.jobHiringnum = jobHiringnum;
		this.jobSalary = jobSalary;
		this.jobArea = jobArea;
		this.jobDesc = jobDesc;
		this.jobState = jobState;
	}
	
	//��ҵ�û����ְλ
	public Job(Company company, String jobName, int jobHiringnum, String jobSalary, String jobArea,
			String jobAgeRequirements, String jobEducationRequirements, String jobSexRequirements,
			String jobForeignLanguages, String jobDesc, int jobState) {
		super();
		this.company = company;
		this.jobName = jobName;
		this.jobHiringnum = jobHiringnum;
		this.jobSalary = jobSalary;
		this.jobArea = jobArea;
		this.jobAgeRequirements = jobAgeRequirements;
		this.jobEducationRequirements = jobEducationRequirements;
		this.jobSexRequirements = jobSexRequirements;
		this.jobForeignLanguages = jobForeignLanguages;
		this.jobDesc = jobDesc;
		this.jobState = jobState;
	}

	//ְҵ��Ϣ�޸ģ�����Ա��
	public Job(int jobId, Company company, String jobName, int jobHiringnum, String jobSalary, String jobArea,
			String jobAgeRequirements, String jobEducationRequirements, String jobSexRequirements,
			String jobForeignLanguages, String jobDesc, int jobState) {
		super();
		this.jobId = jobId;
		this.company = company;
		this.jobName = jobName;
		this.jobHiringnum = jobHiringnum;
		this.jobSalary = jobSalary;
		this.jobArea = jobArea;
		this.jobAgeRequirements = jobAgeRequirements;
		this.jobEducationRequirements = jobEducationRequirements;
		this.jobSexRequirements = jobSexRequirements;
		this.jobForeignLanguages = jobForeignLanguages;
		this.jobDesc = jobDesc;
		this.jobState = jobState;
	}

	//ȫ��
	public Job(int jobId, Company company, String jobName, int jobHiringnum, String jobSalary, String jobArea,
			String jobAgeRequirements, String jobEducationRequirements, String jobSexRequirements,
			String jobForeignLanguages, String jobDesc, int jobState, int jobViewNumber, int jobApplicantsNumber,
			int jobPassingNumber) {
		super();
		this.jobId = jobId;
		this.company = company;
		this.jobName = jobName;
		this.jobHiringnum = jobHiringnum;
		this.jobSalary = jobSalary;
		this.jobArea = jobArea;
		this.jobAgeRequirements = jobAgeRequirements;
		this.jobEducationRequirements = jobEducationRequirements;
		this.jobSexRequirements = jobSexRequirements;
		this.jobForeignLanguages = jobForeignLanguages;
		this.jobDesc = jobDesc;
		this.jobState = jobState;
		this.jobViewNumber = jobViewNumber;
		this.jobApplicantsNumber = jobApplicantsNumber;
		this.jobPassingNumber = jobPassingNumber;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getJobHiringnum() {
		return jobHiringnum;
	}

	public void setJobHiringnum(int jobHiringnum) {
		this.jobHiringnum = jobHiringnum;
	}

	public String getJobSalary() {
		return jobSalary;
	}

	public void setJobSalary(String jobSalary) {
		this.jobSalary = jobSalary;
	}

	public String getJobArea() {
		return jobArea;
	}

	public void setJobArea(String jobArea) {
		this.jobArea = jobArea;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public int getJobState() {
		return jobState;
	}

	public void setJobState(int jobState) {
		this.jobState = jobState;
	}

	public String getJobAgeRequirements() {
		return jobAgeRequirements;
	}

	public void setJobAgeRequirements(String jobAgeRequirements) {
		this.jobAgeRequirements = jobAgeRequirements;
	}

	public String getJobEducationRequirements() {
		return jobEducationRequirements;
	}

	public void setJobEducationRequirements(String jobEducationRequirements) {
		this.jobEducationRequirements = jobEducationRequirements;
	}

	public String getJobSexRequirements() {
		return jobSexRequirements;
	}

	public void setJobSexRequirements(String jobSexRequirements) {
		this.jobSexRequirements = jobSexRequirements;
	}

	public String getJobForeignLanguages() {
		return jobForeignLanguages;
	}

	public void setJobForeignLanguages(String jobForeignLanguages) {
		this.jobForeignLanguages = jobForeignLanguages;
	}

	public int getJobViewNumber() {
		return jobViewNumber;
	}

	public void setJobViewNumber(int jobViewNumber) {
		this.jobViewNumber = jobViewNumber;
	}

	public int getJobApplicantsNumber() {
		return jobApplicantsNumber;
	}

	public void setJobApplicantsNumber(int jobApplicantsNumber) {
		this.jobApplicantsNumber = jobApplicantsNumber;
	}

	public int getJobPassingNumber() {
		return jobPassingNumber;
	}

	public void setJobPassingNumber(int jobPassingNumber) {
		this.jobPassingNumber = jobPassingNumber;
	}

}
