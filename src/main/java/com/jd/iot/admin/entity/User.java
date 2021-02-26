package com.jd.iot.admin.entity;

import com.jd.iot.admin.vo.UserVO;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//User Entity
@Entity
@Table(name = "iot_hub_rbac_user")
public class User {

    @Id
    @Digits(integer = 11, fraction = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 64)
    @Column(name = "user_id", nullable = false)
    private String userid;

    @Size(min = 1, max = 64)
    @Column(name = "login_name", nullable = false)
    private String loginname;

    @NotNull
    @Size(min = 8, max = 256)
    private String password;

    @Size(max = 64)
    @Column(name = "real_name")
    private String realname;

    @Size(max = 256)
    @Column(name = "org_id", nullable = false)
    private String orgid;

    @Digits(integer = 4, fraction = 0)
    @Column(name = "is_deleted")
    private Integer isdeleted;

    @Size(max = 64)
    @Email
    private String email;

    @Digits(integer = 4, fraction = 0)
    private Integer sex;

    @Size(max = 256)
    private String comment;

    @Column(name = "create_time")
    private Timestamp createtime;

    @Column(name = "update_time")
    private Timestamp updatetime;

    @Size(max = 64)
    @Column(name = "user_status")
    private String userstatus;

    @Digits(integer = 20, fraction = 0)
    @Column(name = "user_group_id")
    private Long usergroupid;

    @Digits(integer = 20, fraction = 0)
    @Column(name = "tenant_id")
    private Long tenantid;

    @Digits(integer = 1, fraction = 0)
    @Column(name = "is_tenant_admin")
    private Integer istenantadmin;

    @Digits(integer = 1, fraction = 0)
    @Column(name = "is_forbidden")
    private Integer isforbidden;

    @Size(max = 256)
    @Column(name = "full_parent_id")
    private String fullparentid;

    @Size(max = 20)
    private String mobile;

    public User() {

    }

    public User(UserVO uservo) {
        this.id = uservo.getId();
        this.userid = uservo.getUserid();
        this.loginname = uservo.getLoginname();
        this.password = uservo.getPassword();
        this.realname = uservo.getRealname();
        this.orgid = uservo.getOrgid();
        this.isdeleted = uservo.getIsdeleted();
        this.email = uservo.getEmail();
        this.sex = uservo.getSex();
        this.comment = uservo.getComment();
        this.createtime = uservo.getCreatetime();
        this.updatetime = uservo.getUpdatetime();
        this.tenantid = uservo.getTenantid();
        this.userstatus = uservo.getUserstatus();
        this.usergroupid = uservo.getUsergroupid();
        this.tenantid = uservo.getTenantid();
        this.istenantadmin = uservo.getIstenantadmin();
        this.isforbidden = uservo.getIsforbidden();
        this.fullparentid = uservo.getFullparentid();
        this.mobile = uservo.getMobile();
    }

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

    public Long getUsergroupid() {
        return usergroupid;
    }

    public void setUsergroupid(Long usergroupid) {
        this.usergroupid = usergroupid;
    }

    public Long getTenantid() {
        return tenantid;
    }

    public void setTenantid(Long tenantid) {
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