package com.jd.iot.admin.vo;

import java.sql.Timestamp;

import com.jd.iot.admin.entity.Organization;

public class OrganizationVO {

    private Long id;

    private String orgname;

    private Long parentorgid;

    private Long orglevel;

    private Long orgtype;

    private String orgtypename;

    private Long orgcatlog;

    private String baseorgcode;

    private String tenantid;

    private Timestamp updatetime;

    private Timestamp createtime;

    private Integer isdeleted;

    private String fullparentid;

    private Integer ishavechild;

    public OrganizationVO() {
    }

    public OrganizationVO(Organization organization) {
        id = organization.getId();
        orgname = organization.getOrgname();
        parentorgid = organization.getParentorgid();
        orglevel = organization.getOrglevel();
        orgtype = organization.getOrgtype();
        orgtypename = organization.getOrgtypename();
        orgcatlog = organization.getOrgcatlog();
        baseorgcode = organization.getBaseorgcode();
        tenantid = organization.getTenantid();
        createtime = organization.getCreatetime();
        updatetime = organization.getUpdatetime();
        isdeleted = organization.getIsdeleted();
        fullparentid = organization.getFullparentid();
        ishavechild = organization.getIshavechild();
    }

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
