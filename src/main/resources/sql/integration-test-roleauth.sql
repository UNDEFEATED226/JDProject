DROP TABLE IF EXISTS iot_hub_rbac_role_auth;

CREATE TABLE iot_hub_rbac_role_auth(
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  role_id int(11),
  auth_id int(11),
  is_deleted tinyint(1),
  create_time datetime,
  update_time timestamp
)
;

INSERT INTO iot_hub_rbac_role_auth VALUES (1,null,null,0,null,'2021-2-18 10:23:00');
INSERT INTO iot_hub_rbac_role_auth VALUES (2,null,null,0,null,'2021-2-18 10:26:00');