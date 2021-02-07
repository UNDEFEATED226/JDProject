drop table IF EXISTS iot_hub_rbac_resource;

CREATE TABLE iot_hub_rbac_resource(
  id int(11) AUTO_INCREMENT PRIMARY KEY,
  module_name varchar(64),
  res_code varchar(64),
  res_name varchar(512),
  res_uri varchar(512),
  res_type_id int(11),
  parent_id int(11),
  level int(11),
  has_child tinyint(4),
  description varchar(256),
  route_code varchar(255),
  full_name varchar(255),
  `order` int(255),
  selected tinyint(1),
  is_show tinyint(1),
  title varchar(255),
  is_deleted tinyint(1),
  create_time datetime,
  update_time timestamp
)
;

INSERT INTO iot_hub_rbac_resource VALUES (1,null,null,'资源',null,0,null,null,null,null,null,null,null,null,null,null,0,null,'2021-02-4 10:30:01');
INSERT INTO iot_hub_rbac_resource VALUES (2,null,null,'资源',null,0,null,null,null,null,null,null,null,null,null,null,0,null,'2021-02-4 10:30:01');
INSERT INTO iot_hub_rbac_resource VALUES (3,null,null,'资源',null,0,null,null,null,null,null,null,null,null,null,null,0,null,'2021-02-4 10:30:01');
INSERT INTO iot_hub_rbac_resource VALUES (4,null,null,'资源',null,0,null,null,null,null,null,null,null,null,null,null,0,null,'2021-02-4 10:30:01');
INSERT INTO iot_hub_rbac_resource VALUES (5,null,null,'资源',null,0,null,null,null,null,null,null,null,null,null,null,0,null,'2021-02-4 10:30:01');
INSERT INTO iot_hub_rbac_resource VALUES (6,null,null,'资源',null,0,null,null,null,null,null,null,null,null,null,null,0,null,'2021-02-4 10:30:01');