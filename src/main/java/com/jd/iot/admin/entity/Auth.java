package com.jd.iot.admin.entity;

import com.jd.iot.admin.vo.AuthVO;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "iot_hub_rbac_auth")
public class Auth {

    @Id
    @NotNull
    @Digits(integer = 11, fraction = 0)
    private Long id;

    @Column(name = "auth_name")
    @Size(max = 64)
    private String authname;

    @Size(max = 256)
    private String description;

    @Digits(integer = 11, fraction = 0)
    @Column(name = "res_id")
    private Long resid;

    @Digits(integer = 1, fraction = 0)
    @Column(name = "is_deleted")
    private Integer isdeleted;

    @Column(name = "create_time")
    private Timestamp createtime;

    @Column(name = "update_time", nullable = false)
    private Timestamp updatetime;

    public Auth() {

    }

    public Auth(AuthVO authvo) {
        id = authvo.getId();
        authname = authvo.getAuthname();
        description = authvo.getDescription();
        resid = authvo.getResid();
        isdeleted = authvo.getIsdeleted();
        createtime = authvo.getCreatetime();
        updatetime = authvo.getUpdatetime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthname() {
        return authname;
    }

    public void setAuthname(String authname) {
        this.authname = authname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getResid() {
        return resid;
    }

    public void setResid(Long resid) {
        this.resid = resid;
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
