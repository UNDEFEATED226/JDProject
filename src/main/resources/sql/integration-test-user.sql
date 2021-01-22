drop table IF EXISTS iot_hub_rbac_user;

CREATE TABLE `iot_hub_rbac_user` (
    `id` INT(11) PRIMARY KEY,
    `user_id` VARCHAR(64),
    `login_name` VARCHAR(64),
    `password` VARCHAR(256),
    `real_name` VARCHAR(64),
    `org_id` VARCHAR(256),
    `is_deleted` TINYINT(4),
    `email` VARCHAR(64),
    `sex` TINYINT(4),
    `comment` VARCHAR(256),
    `create_time` DATETIME,
    `update_time` DATETIME,
    `user_status` VARCHAR(64),
    `user_group_id` BIGINT(20),
    `tenant_id` BIGINT(20),
    `is_tenant_admin` TINYINT(1),
    `is_forbidden` TINYINT(1),
    `full_parent_id` VARCHAR(256),
    `mobile` VARCHAR(20)
)DEFAULT CHARSET=utf8;

INSERT INTO iot_hub_rbac_user (id, user_id, login_name, password, real_name, org_id, is_deleted, email, sex, comment, create_time, update_time, user_status, user_group_id, tenant_id, is_tenant_admin, is_forbidden, full_parent_id, mobile) VALUES (107, 'jd-iot-8f4b2879214122eab344fa163e9acd22', 'jinfeng', '9bd18a77083f310080399caf61dbfaa6', '系统管理员', '26', 0, 'test@jd.com', 1, 'test', '2020-12-09 14:58:18', NULL, '0', NULL, 334, 0, 0, NULL, NULL);
INSERT INTO iot_hub_rbac_user (id, user_id, login_name, password, real_name, org_id, is_deleted, email, sex, comment, create_time, update_time, user_status, user_group_id, tenant_id, is_tenant_admin, is_forbidden, full_parent_id, mobile) VALUES (188,'jd-iot-8f4b2879214122eab344fa163e9acd22', 'jinfeng', '9bd18a77083f310080399caf61dbfaa6', '系统破坏猿', '26', 0, 'test@jd.com', 1, 'test', '2020-12-09 14:58:18', NULL, '0', NULL, 334, 0, 0, NULL, NULL);