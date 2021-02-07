package com.jd.iot.admin.vo;

import java.sql.Timestamp;

import com.jd.iot.admin.entity.Resource;

public class ResourceVO {

    private Long id;

    private String modulename;

    private String rescode;

    private String resname;

    private String resuri;

    private Long restypeid;

    private Long parentid;

    private Long level;

    private Integer haschild;

    private String description;

    private String routecode;

    private String fullname;

    /*private Long orderBy;*/

    private Integer selected;

    private Integer isshow;

    private String title;

    private Integer isdeleted;

    private Timestamp createtime;

    private Timestamp updatetime;

    public ResourceVO() {
    }

    public ResourceVO(Resource resource) {
        id = resource.getId();
        modulename = resource.getModulename();
        rescode = resource.getRescode();
        resname = resource.getResname();
        resuri = resource.getResuri();
        restypeid = resource.getRestypeid();
        parentid = resource.getParentid();
        level = resource.getLevel();
        haschild = resource.getHaschild();
        description = resource.getDescription();
        routecode = resource.getRoutecode();
        fullname = resource.getFullname();
        /*orderBy = resource.getOrder();*/
        selected = resource.getSelected();
        isshow = resource.getIsshow();
        title = resource.getTitle();
        isdeleted = resource.getIsdeleted();
        createtime = resource.getCreatetime();
        updatetime = resource.getUpdatetime();
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
