package com.jd.iot.admin.vo;

import java.sql.Timestamp;

import com.jd.iot.admin.entity.RoleAuth;

public class RoleAuthVO {

    private Long id;

    private Long roleid;

    private String rolename;

    private Long authid;

    private String authname;
    
    private String resname;

    private Integer isdeleted;

    private Timestamp createtime;

    private Timestamp updatetime;

    public RoleAuthVO() {

    }

    public RoleAuthVO(RoleAuth roleauth) {
        id = roleauth.getId();
        roleid = roleauth.getRoleid();
        authid = roleauth.getAuthid();
        isdeleted = roleauth.getIsdeleted();
        createtime = roleauth.getCreatetime();
        updatetime = roleauth.getUpdatetime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public Long getAuthid() {
        return authid;
    }

    public void setAuthid(Long authid) {
        this.authid = authid;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }

    public Integer getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Integer isdeleted) {
        this.isdeleted = isdeleted;
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

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getAuthname() {
        return authname;
    }

    public void setAuthname(String authname) {
        this.authname = authname;
    }
}
