package com.jd.iot.admin.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.jd.iot.admin.vo.ResourceVO;

//资源实体
@Entity
@Table(name = "iot_hub_rbac_resource")
public class Resource {

    @NotNull
    @Id
    @Digits(integer = 11, fraction = 0)
    private Long id;

    @Size(max = 64)
    @Column(name = "module_name")
    private String modulename;

    @Size(max = 64)
    @Column(name = "res_code", unique = true)
    private String rescode;

    @Size(max = 512)
    @Column(name = "res_name")
    private String resname;

    @Size(max = 512)
    @Column(name = "res_uri")
    private String resuri;

    @Digits(integer = 11, fraction = 0)
    @Column(name = "res_type_id")
    private Long restypeid;

    @Digits(integer = 11, fraction = 0)
    @Column(name = "parent_id")
    private Long parentid;

    @Digits(integer = 11, fraction = 0)
    private Long level;

    @Digits(integer = 4, fraction = 0)
    @Column(name = "has_child")
    private Integer haschild;

    @Size(max = 256)
    private String description;

    @Size(max = 255)
    @Column(name = "route_code")
    private String routecode;

    @Size(max = 255)
    @Column(name = "full_name")
    private String fullname;

    /*@Column(name = "`ORDER`")
    @Digits(integer = 255, fraction = 0)
    private Long orderBy;*/

    @Digits(integer = 1, fraction = 0)
    private Integer selected;

    @Digits(integer = 1, fraction = 0)
    @Column(name = "is_show")
    private Integer isshow;

    @Size(max = 255)
    private String title;

    @Digits(integer = 1, fraction = 0)
    @Column(name = "is_deleted")
    private Integer isdeleted;

    @Column(name = "create_time")
    private Timestamp createtime;

    @NotNull
    @Column(name = "update_time")
    private Timestamp updatetime;

    public Resource() {
    }

    public Resource(ResourceVO resourcevo) {
        id = resourcevo.getId();
        modulename = resourcevo.getModulename();
        rescode = resourcevo.getRescode();
        resname = resourcevo.getResname();
        resuri = resourcevo.getResuri();
        restypeid = resourcevo.getRestypeid();
        parentid = resourcevo.getParentid();
        level = resourcevo.getLevel();
        haschild = resourcevo.getHaschild();
        description = resourcevo.getDescription();
        routecode = resourcevo.getRoutecode();
        fullname = resourcevo.getFullname();
        /*orderBy = resourcevo.getOrder();*/
        selected = resourcevo.getSelected();
        isshow = resourcevo.getIsshow();
        title = resourcevo.getTitle();
        isdeleted = resourcevo.getIsdeleted();
        createtime = resourcevo.getCreatetime();
        updatetime = resourcevo.getUpdatetime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename;
    }

    public String getRescode() {
        return rescode;
    }

    public void setRescode(String rescode) {
        this.rescode = rescode;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }

    public String getResuri() {
        return resuri;
    }

    public void setResuri(String resuri) {
        this.resuri = resuri;
    }

    public Long getRestypeid() {
        return restypeid;
    }

    public void setRestypeid(Long restypeid) {
        this.restypeid = restypeid;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Integer getHaschild() {
        return haschild;
    }

    public void setHaschild(Integer haschild) {
        this.haschild = haschild;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoutecode() {
        return routecode;
    }

    public void setRoutecode(String routecode) {
        this.routecode = routecode;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /*public Long getOrder() {
        return orderBy;
    }

    public void setOrder(Long order) {
        this.orderBy = order;
    }*/

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public Integer getIsshow() {
        return isshow;
    }

    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
