DROP TABLE IF EXISTS iot_hub_rbac_auth;

CREATE TABLE iot_hub_rbac_auth (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  auth_name varchar(64) DEFAULT NULL,
  description varchar(256) DEFAULT NULL,
  res_id int(11) DEFAULT NULL,
  is_deleted tinyint(1) DEFAULT '0',
  create_time datetime DEFAULT CURRENT_TIMESTAMP,
  update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
;

INSERT INTO iot_hub_rbac_auth VALUES (1,'CREATE_PERMISSION','添加',1,0,'2020-09-11 16:16:13','2020-09-11 16:16:13');
INSERT INTO iot_hub_rbac_auth VALUES (2,'CREATE_PERMISSION','添加',2,0,'2020-02-07 16:16:13','2020-02-07 16:16:13');