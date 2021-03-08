package com.jd.iot.admin.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import com.jd.iot.admin.vo.UserRoleVO;

//Role Entity
@Entity
@Table(name = "iot_hub_rbac_user_role")
public class UserRole {
    @Id
    @Digits(integer = 11, fraction = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Digits(integer = 11, fraction = 0)
    @Column(name = "user_id")
    private Long userid;

    @Digits(integer = 11, fraction = 0)
    @Column(name = "role_id")
    private Long roleid;

    @Digits(integer = 1, fraction = 0)
    @Column(name = "is_deleted")
    private Integer isdeleted;

    @Column(name = "create_time", nullable = false)
    private Timestamp createtime;

    @Column(name = "update_time", nullable = false)
    private Timestamp updatetime;

    public UserRole() {

    }

    public UserRole(UserRoleVO userrolevo) {
        id = userrolevo.getId();
        userid = userrolevo.getUserid();
        roleid = userrolevo.getRoleid();
        isdeleted = userrolevo.getIsdeleted();
        createtime = userrolevo.getCreatetime();
        updatetime = userrolevo.getUpdatetime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
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
