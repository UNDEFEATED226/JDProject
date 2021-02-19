package com.jd.iot.admin.vo;

import com.jd.iot.admin.entity.Resource;
import java.sql.Timestamp;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

public class ResourceVO {

    private Long id;

    @Size(max = 64)
    private String modulename;

    @Size(max = 64)
    private String rescode;

    @Size(max = 512)
    private String resname;

    @Size(max = 512)
    private String resuri;

    @Digits(integer = 11, fraction = 0)
    private Long restypeid;

    @Digits(integer = 11, fraction = 0)
    private Long parentid;

    @Digits(integer = 11, fraction = 0)
    private Long level;

    @Digits(integer = 4, fraction = 0)
    private Integer haschild;

    @Size(max = 256)
    private String description;

    @Size(max = 255)
    private String routecode;

    @Size(max = 255)
    private String fullname;

    /*@Column(name = "`ORDER`")
    @Digits(integer = 255, fraction = 0)
    private Long orderBy;*/

    @Digits(integer = 1, fraction = 0)
    private Integer selected;

    @Digits(integer = 1, fraction = 0)
    private Integer isshow;

    @Size(max = 255)
    private String title;

    @Digits(integer = 1, fraction = 0)
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
