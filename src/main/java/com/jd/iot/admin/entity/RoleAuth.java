package com.jd.iot.admin.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "iot_hub_rbac_role_auth")
public class RoleAuth {

    @Id
    @NotNull
    @Digits(integer = 11, fraction = 0)
    private Long id;

    @Column(name = "role_id")
    @Digits(integer = 11, fraction = 0)
    private Long roleid;

    @Column(name = "auth_id")
    @Digits(integer = 11, fraction = 0)
    private Long authid;

    @Column(name = "is_deleted")
    @Digits(integer = 1, fraction = 0)
    private Integer isdeleted;

    @Column(name = "create_time")
    private Timestamp createtime;

    @Column(name = "update_time", nullable = false)
    private Timestamp updatetime;
}
