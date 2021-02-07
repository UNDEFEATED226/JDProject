drop table IF EXISTS iot_hub_rbac_role;

CREATE TABLE iot_hub_rbac_role (
  id int(11) NOT NULL AUTO_INCREMENT,
  role_name varchar(64) DEFAULT NULL,
  role_type int(11) DEFAULT NULL,
  description varchar(256) DEFAULT NULL,
  tenant_id bigint(20) DEFAULT NULL,
  is_deleted tinyint(1) DEFAULT '0',
  is_system tinyint(1) DEFAULT NULL,
  create_time datetime DEFAULT CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL,
  role_code varchar(100) DEFAULT NULL,
  is_forbidden tinyint(1) DEFAULT '0',
  is_default tinyint(1) DEFAULT '0'
) 
;

INSERT INTO iot_hub_rbac_role VALUES (1,'系统管理员',1,null,null,0,null,null,'2021-02-05 14:32:22',null,null,null);
INSERT INTO iot_hub_rbac_role VALUES (2,'系统管理员',2,null,null,0,null,null,'2021-02-05 14:32:22',null,null,null);