package com.jd.iot.admin.vo;

import com.jd.iot.admin.entity.Tenant;
import java.sql.Timestamp;

public class TenantVO {

    private Long id;

    private String name;

    private String businessassignment;

    private Integer isdeleted;

    private Timestamp createtime;

    private Timestamp updatetime;

    private Long adminuserid;

    public TenantVO() {

    }

    public TenantVO(Tenant tenant) {
        id = tenant.getId();
        name = tenant.getName();
        businessassignment = tenant.getBusinessassignment();
        isdeleted = tenant.getIsdeleted();
        createtime = tenant.getCreatetime();
        updatetime = tenant.getUpdatetime();
        adminuserid = tenant.getAdminuserid();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessassignment() {
        return businessassignment;
    }

    public void setBusinessassignment(String businessassignment) {
        this.businessassignment = businessassignment;
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

    public Long getAdminuserid() {
        return adminuserid;
    }

    public void setAdminuserid(Long adminuserid) {
        this.adminuserid = adminuserid;
    }
}
