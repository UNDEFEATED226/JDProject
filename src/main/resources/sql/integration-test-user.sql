drop table IF EXISTS iot_hub_rbac_user;

CREATE TABLE iot_hub_rbac_user(
  	id int(11) PRIMARY KEY AUTO_INCREMENT,
    user_id varchar(64),
    login_name varchar(64),
    password varchar(256),
    real_name varchar(64),
    org_id varchar(256),
    is_deleted tinyint(4),
    email varchar(64),
    sex tinyint(4),
    comment varchar(256),
    create_time datetime,
    update_time datetime,
    user_status varchar(64),
    user_group_id bigint(20),
    tenant_id bigint(20),
    is_tenant_admin tinyint(1),
    is_forbidden tinyint(1),
    full_parent_id varchar(256),
    mobile varchar(20)
);

INSERT INTO iot_hub_rbac_user VALUES (107, 'jd-iot-8f4b2879214122eab344fa163e9acd22', 'jinfeng', '9bd18a77083f310080399caf61dbfaa6', '系统管理员', '26', 0, 'test@jd.com', 1, 'test', '2020-12-09 14:58:18', NULL, '0', NULL, 334, 0, 0, NULL, NULL);
