package test;

public class Resume {

	
	private Integer id;
	private String name;
	private String username;
	private Integer sex;
	private Integer age;
	private String nation;
	private String education;
	private String major;
	private String college;
	private String address;
	private String phone;
	private String email;
	private String stuExperience;
	private String jobExperience;
	private String assess;
	
	public Resume(){}
	
	public Resume(String name, String username, Integer sex, Integer age,
			String nation, String education, String major, String college,
			String address, String phone, String email, String stuExperience,
			String jobExperience, String assess, String dataSource) {
		super();
		this.name = name;
		this.username = username;
		this.sex = sex;
		this.age = age;
		this.nation = nation;
		this.education = education;
		this.major = major;
		this.college = college;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.stuExperience = stuExperience;
		this.jobExperience = jobExperience;
		this.assess = assess;
		this.dataSource = dataSource;
	}
	private String dataSource;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
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
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStuExperience() {
		return stuExperience;
	}
	public void setStuExperience(String stuExperience) {
		this.stuExperience = stuExperience;
	}
	public String getJobExperience() {
		return jobExperience;
	}
	public void setJobExperience(String jobExperience) {
		this.jobExperience = jobExperience;
	}
	public String getAssess() {
		return assess;
	}
	public void setAssess(String assess) {
		this.assess = assess;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	
	
}
