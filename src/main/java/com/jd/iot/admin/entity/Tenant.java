package com.jd.iot.admin.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "iot_hub_rbac_tenant")
public class Tenant {

    @Id
    @NotNull
    @Digits(integer = 20, fraction = 0)
    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    @Column(name = "business_assignment")
    private String businessassignment;

    @Digits(integer = 1, fraction = 0)
    @Column(name = "is_deleted")
    private Integer isdeleted;

    @Column(name = "create_time", nullable = false)
    private Timestamp createtime;

    @Column(name = "update_time", nullable = false)
    private Timestamp updatetime;

    @Digits(integer = 11, fraction = 0)
    @Column(name = "admin_user_id")
    private Long adminuserid;

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
