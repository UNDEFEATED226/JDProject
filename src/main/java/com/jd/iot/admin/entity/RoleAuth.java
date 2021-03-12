package com.jd.iot.admin.entity;

import com.jd.iot.admin.vo.RoleAuthVO;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

//角色权限
@Entity
@Table(name = "iot_hub_rbac_role_auth")
public class RoleAuth {

    @Id
    @Digits(integer = 11, fraction = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_id")
    @Digits(integer = 11, fraction = 0)
    private Long roleid;

    @Column(name = "auth_id")
    @Digits(integer = 11, fraction = 0)
    private Long authid;

    @Column(name = "is_deleted")
    @Digits(integer = 1, fraction = 0)
    private Integer isdeleted;

    @Column(name = "create_time")
    private Timestamp createtime;

    @Column(name = "update_time", nullable = false)
    private Timestamp updatetime;

    public RoleAuth() {

    }

    public RoleAuth(RoleAuthVO roleauthvo) {
        id = roleauthvo.getId();
        roleid = roleauthvo.getRoleid();
        authid = roleauthvo.getAuthid();
        isdeleted = roleauthvo.getIsdeleted();
        createtime = roleauthvo.getCreatetime();
        updatetime = roleauthvo.getUpdatetime();

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