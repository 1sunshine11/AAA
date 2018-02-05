package model;

public class Usermodel extends Page {
    private Integer id;
   
    private String userpass;
    private String name;
    private Integer sex;
    private String email;
    private String descr;
    private String birthdate;
    private String birthStart;
    private String birthEnd;
    private Integer isadmin;
    private String  createtime;
    private String createtimeStart;
    private String createtimeEnd;
    private String  updatetime;
    private String updatetimeStart;
    private String updatetimeEnd;
    public String getBirthStart() {
		return birthStart;
	}
	public void setBirthStart(String birthStart) {
		this.birthStart = birthStart;
	}
	public String getBirthEnd() {
		return birthEnd;
	}
	public void setBirthEnd(String birthEnd) {
		this.birthEnd = birthEnd;
	}
	public String getCreatetimeStart() {
		return createtimeStart;
	}
	public void setCreatetimeStart(String createtimeStart) {
		this.createtimeStart = createtimeStart;
	}
	public String getCreatetimeEnd() {
		return createtimeEnd;
	}
	public void setCreatetimeEnd(String createtimeEnd) {
		this.createtimeEnd = createtimeEnd;
	}
	public String getUpdatetimeStart() {
		return updatetimeStart;
	}
	public void setUpdatetimeStart(String updatetimeStart) {
		this.updatetimeStart = updatetimeStart;
	}
	public String getUpdatetimeEnd() {
		return updatetimeEnd;
	}
	public void setUpdatetimeEnd(String updatetimeEnd) {
		this.updatetimeEnd = updatetimeEnd;
	}
	private Integer isdelete;
    private Integer iseffect;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public Integer getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(Integer isadmin) {
		this.isadmin = isadmin;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public Integer getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	public Integer getIseffect() {
		return iseffect;
	}
	public void setIseffect(Integer iseffect) {
		this.iseffect = iseffect;
	}
	
    
}
