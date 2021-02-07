package com.jd.iot.admin.vo;

import java.sql.Timestamp;

import com.jd.iot.admin.entity.Role;

public class RoleVO {

    private Long id;

    private String rolename;

    private Long roletype;

    private String description;

    private Long tenantid;

    private Integer isdeleted;

    private Integer issystem;

    private Timestamp createtime;

    private Timestamp updatetime;

    private String rolecode;

    private Integer isforbidden;

    private Integer isdefault;

    public RoleVO() {
    }

    public RoleVO(Role role) {
        id = role.getId();
        rolename = role.getRolename();
        roletype = role.getRoletype();
        description = role.getDescription();
        tenantid = role.getTenantid();
        isdeleted = role.getIsdeleted();
        issystem = role.getIssystem();
        createtime = role.getCreatetime();
        updatetime = role.getUpdatetime();
        rolecode = role.getRolecode();
        isforbidden = role.getIsforbidden();
        isdefault = role.getIsdefault();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Long getRoletype() {
        return roletype;
    }

    public void setRoletype(Long roletype) {
        this.roletype = roletype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTenantid() {
        return tenantid;
    }

    public void setTenantid(Long tenantid) {
        this.tenantid = tenantid;
    }

    public Integer getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Integer isdeleted) {
        this.isdeleted = isdeleted;
    }

    public Integer getIssystem() {
        return issystem;
    }

    public void setIssystem(Integer issystem) {
        this.issystem = issystem;
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

    public String getRolecode() {
        return rolecode;
    }

    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    public Integer getIsforbidden() {
        return isforbidden;
    }

    public void setIsforbidden(Integer isforbidden) {
        this.isforbidden = isforbidden;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

}
