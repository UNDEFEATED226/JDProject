package com.jd.iot.admin.vo;

import com.jd.iot.admin.entity.User;
import java.sql.Timestamp;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserVO {

    private Long id;

    private String userid;

    @NotNull
    @Size(min = 1, max = 64)
    private String loginname;

    @NotNull
    @Size(min = 8, max = 256)
    private String password;

    @Size(max = 64)
    private String realname;

    @Size(max = 256)
    private String orgid;

    @Digits(integer = 4, fraction = 0)
    private Integer isdeleted;

    @Size(max = 64)
    @Email
    private String email;

    @Digits(integer = 4, fraction = 0)
    private Integer sex;

    @Size(max = 256)
    private String comment;

    private Timestamp createtime;

    private Timestamp updatetime;

    @Size(max = 64)
    private String userstatus;

    @Digits(integer = 20, fraction = 0)
    private Long usergroupid;

    @Digits(integer = 20, fraction = 0)
    private Long tenantid;

    private Integer istenantadmin;

    private Integer isforbidden;

    @Size(max = 256)
    private String fullparentid;

    @Size(max = 20)
    private String mobile;

    public UserVO() {
    }

    public UserVO(User user) {
        id = user.getId();
        userid = user.getUserid();
        loginname = user.getLoginname();
        password = user.getPassword();
        realname = user.getRealname();
        orgid = user.getOrgid();
        isdeleted = user.getIsdeleted();
        email = user.getEmail();
        sex = user.getSex();
        comment = user.getComment();
        createtime = user.getCreatetime();
        updatetime = user.getUpdatetime();
        tenantid = user.getTenantid();
        userstatus = user.getUserstatus();
        usergroupid = user.getUsergroupid();
        tenantid = user.getTenantid();
        istenantadmin = user.getIstenantadmin();
        isforbidden = user.getIsforbidden();
        fullparentid = user.getFullparentid();
        mobile = user.getMobile();
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
