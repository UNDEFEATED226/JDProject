package com.jd.iot.admin.vo;

import com.jd.iot.admin.entity.Auth;
import java.sql.Timestamp;
import javax.validation.constraints.Size;

public class AuthVO {
    
    private Long id;
    
    private String authname;

    @Size(max = 256)
    private String description;

    private Long resid;
    
    private String resname;

    private Integer isdeleted;

    private Timestamp createtime;

    private Timestamp updatetime;

    public AuthVO() {

    }

    public AuthVO(Auth auth) {
        id = auth.getId();
        authname = auth.getAuthname();
        description = auth.getDescription();
        resid = auth.getResid();
        isdeleted = auth.getIsdeleted();
        createtime = auth.getCreatetime();
        updatetime = auth.getUpdatetime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
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
