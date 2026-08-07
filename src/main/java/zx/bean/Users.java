package zx.bean;

import java.time.LocalDateTime;


public class Users {
	private Integer id;
	private String tel;
	private String name;
	private String pwd;
	private String email;
	private String sex;
	private LocalDateTime createtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public LocalDateTime getCreatetime() {
		return createtime;
	}
	public void setCreatetime(LocalDateTime createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", tel=" + tel + ", name=" + name + ", pwd=" + pwd + ", email=" + email + ", sex="
				+ sex + ", createtime=" + createtime + ", getId()=" + getId() + ", getTel()=" + getTel()
				+ ", getName()=" + getName() + ", getPwd()=" + getPwd() + ", getEmail()=" + getEmail() + ", getSex()="
				+ getSex() + ", getCreatetime()=" + getCreatetime() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	/**
	 * 
	 */
	public Users() {
		super();
	}
	/**
	 * @param id
	 * @param tel
	 * @param name
	 * @param pwd
	 * @param email
	 * @param sex
	 * @param createtime
	 */
	public Users(Integer id, String tel, String name, String pwd, String email, String sex, LocalDateTime createtime) {
		super();
		this.id = id;
		this.tel = tel;
		this.name = name;
		this.pwd = pwd;
		this.email = email;
		this.sex = sex;
		this.createtime = createtime;
	}

	
}