package com.jd.iot.admin.vo;

import java.sql.Timestamp;

import com.jd.iot.admin.entity.UserRole;

public class UserRoleVO {

    private Long id;

    private Long userid;

    private Long roleid;

    private Integer isdeleted;

    private Timestamp createtime;

    private Timestamp updatetime;

    public UserRoleVO() {

    }

    public UserRoleVO(UserRole userrole) {
        id = userrole.getId();
        userid = userrole.getUserid();
        roleid = userrole.getRoleid();
        isdeleted = userrole.getIsdeleted();
        createtime = userrole.getCreatetime();
        updatetime = userrole.getUpdatetime();
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