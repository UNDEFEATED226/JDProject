package com.jd.iot.admin.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import com.jd.iot.admin.vo.RoleVO;

@Entity
@Table(name = "iot_hub_rbac_role")
public class Role {

    @Id
    @Digits(integer = 11, fraction = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 64)
    @Column(name = "role_name")
    private String rolename;

    @Column(name = "role_type")
    @Digits(integer = 11, fraction = 0)
    private Long roletype;

    @Size(max = 256)
    private String description;

    @Digits(integer = 20, fraction = 0)
    @Column(name = "tenant_id")
    private Long tenantid;

    @Digits(integer = 1, fraction = 0)
    @Column(name = "is_deleted")
    private Integer isdeleted;

    @Digits(integer = 1, fraction = 0)
    @Column(name = "is_system")
    private Integer issystem;

    @Column(name = "create_time")
    private Timestamp createtime;

    @Column(name = "update_time", nullable = false)
    private Timestamp updatetime;

    @Column(name = "role_code")
    @Size(max = 100)
    private String rolecode;

    @Column(name = "is_forbidden")
    @Digits(integer = 1, fraction = 0)
    private Integer isforbidden;

    @Column(name = "is_default")
    @Digits(integer = 1, fraction = 0)
    private Integer isdefault;

    public Role() {
    }

    public Role(RoleVO rolevo) {
        id = rolevo.getId();
        rolename = rolevo.getRolename();
        roletype = rolevo.getRoletype();
        description = rolevo.getDescription();
        tenantid = rolevo.getTenantid();
        isdeleted = rolevo.getIsdeleted();
        issystem = rolevo.getIssystem();
        createtime = rolevo.getCreatetime();
        updatetime = rolevo.getUpdatetime();
        rolecode = rolevo.getRolecode();
        isforbidden = rolevo.getIsforbidden();
        isdefault = rolevo.getIsdefault();
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
