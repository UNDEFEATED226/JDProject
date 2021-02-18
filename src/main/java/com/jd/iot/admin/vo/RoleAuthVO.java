package com.jd.iot.admin.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.jd.iot.admin.entity.RoleAuth;

public class RoleAuthVO {

    private Long id;
    
    private Long roleid;

    private Long authid;

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
}
