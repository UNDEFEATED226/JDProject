package com.example.demo.Entity;

import java.math.BigInteger;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//User Entity
@Entity
@Table(name="iot_hub_rbac_user")
public class User {
	
	@Id
	private Long id;
	
	@Column(name="user_id")
	private String userid;
	
	@Column(name="login_name")
	private String loginname;
	
	private String password;
	
	@Column(name="real_name")
	private String realname;
	
	@Column(name="org_id")
	private String orgid;
	
	@Column(name="is_deleted")
	private Integer isdeleted;
	
	private String email;
	
	private Integer sex;
	
	private String comment;
	
	@Column(name="create_time")
	private Timestamp createtime;
	
	@Column(name="update_time")
	private Timestamp updatetime;
	
	@Column(name="user_status")
	private String userstatus;
	
	@Column(name="user_group_id")
	private BigInteger usergroupid;
	
	@Column(name="tenant_id")
	private BigInteger tenantid;
	
	@Column(name="is_tenant_admin")
	private Integer istenantadmin;
	
	@Column(name="is_forbidden")
	private Integer isforbidden;
	
	@Column(name="full_parent_id")
	private String fullparentid;
	
	private String mobile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public Integer getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	public String getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}

	public BigInteger getUsergroupid() {
		return usergroupid;
	}

	public void setUsergroupid(BigInteger usergroupid) {
		this.usergroupid = usergroupid;
	}

	public BigInteger getTenantid() {
		return tenantid;
	}

	public void setTenantid(BigInteger tenantid) {
		this.tenantid = tenantid;
	}

	public Integer getIstenantadmin() {
		return istenantadmin;
	}

	public void setIstenantadmin(Integer istenantadmin) {
		this.istenantadmin = istenantadmin;
	}

	public Integer getIsforbidden() {
		return isforbidden;
	}

	public void setIsforbidden(Integer isforbidden) {
		this.isforbidden = isforbidden;
	}

	public String getFullparentid() {
		return fullparentid;
	}

	public void setFullparentid(String fullparentid) {
		this.fullparentid = fullparentid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
