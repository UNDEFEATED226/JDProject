DROP TABLE IF EXISTS iot_hub_rbac_tenant;

CREATE TABLE `iot_hub_rbac_tenant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '租户名称',
  `business_assignment` varchar(255) DEFAULT NULL COMMENT '业务归属',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `admin_user_id` int(11) DEFAULT NULL COMMENT '租户管理员user id'
) 
;

INSERT INTO iot_hub_rbac_tenant VALUES (1,'未知租户','业务归属',0,'2021-02-07 16:31:31','2021-02-07 16:31:31',100);
INSERT INTO iot_hub_rbac_tenant VALUES (2,'未知租户','业务归属',0,'2021-02-07 16:31:31','2021-02-07 16:31:31',101);