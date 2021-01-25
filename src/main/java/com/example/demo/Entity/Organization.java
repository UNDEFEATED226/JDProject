package com.example.demo.Entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//Organization Entity
@Entity
@Table(name="iot_hub_rbac_organization")
public class Organization {
	
	@NotNull
	@Id
	private Long id;
	
	@Size(max=64)
	@Column(name="org_name")
	private String orgname;
	
	@Digits(integer=11,fraction=0)
	@Column(name="parent_org_id")
	private Long parentorgid;
	
	@Digits(integer=11,fraction=0)
	@Column(name="org_level")
	private Long orglevel;
	
	@Digits(integer=11,fraction=0)
	@Column(name="org_type")
	private Long orgtype;
	
	@Size(max=64)
	@Column(name="org_type_name")
	private String orgtypename;
	
	@Digits(integer=11,fraction=0)
	@Column(name="org_catlog")
	private Long orgcatlog;
	
	@Size(max=64)
	@Column(name="base_org_code")
	private String baseorgcode;
	
	@Size(max=64)
	@Column(name="tenant_id")
	private String tenantid;
	
	@Column(name="update_time")
	private Timestamp updatetime;
	
	@Column(name="create_time")
	private Timestamp createtime;
	
	@Digits(integer=4,fraction=0)
	@Column(name="is_deleted")
	private Integer isdeleted;
	
	@Size(max=255)
	@Column(name="full_parent_id")
	private String fullparentid;
	
	@Digits(integer=1,fraction=0)
	@Column(name="is_have_child")
	private Integer ishavechild;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public Long getParentorgid() {
		return parentorgid;
	}

	public void setParentorgid(Long parentorgid) {
		this.parentorgid = parentorgid;
	}

	public Long getOrglevel() {
		return orglevel;
	}

	public void setOrglevel(Long orglevel) {
		this.orglevel = orglevel;
	}

	public Long getOrgtype() {
		return orgtype;
	}

	public void setOrgtype(Long orgtype) {
		this.orgtype = orgtype;
	}

	public String getOrgtypename() {
		return orgtypename;
	}

	public void setOrgtypename(String orgtypename) {
		this.orgtypename = orgtypename;
	}

	public Long getOrgcatlog() {
		return orgcatlog;
	}

	public void setOrgcatlog(Long orgcatlog) {
		this.orgcatlog = orgcatlog;
	}

	public String getBaseorgcode() {
		return baseorgcode;
	}

	public void setBaseorgcode(String baseorgcode) {
		this.baseorgcode = baseorgcode;
	}

	public String getTenantid() {
		return tenantid;
	}

	public void setTenantid(String tenantid) {
		this.tenantid = tenantid;
	}

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Integer getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}

	public String getFullparentid() {
		return fullparentid;
	}

	public void setFullparentid(String fullparentid) {
		this.fullparentid = fullparentid;
	}

	public Integer getIshavechild() {
		return ishavechild;
	}

	public void setIshavechild(Integer ishavechild) {
		this.ishavechild = ishavechild;
	}
}
