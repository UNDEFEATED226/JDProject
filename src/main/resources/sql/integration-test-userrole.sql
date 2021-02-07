drop table IF EXISTS iot_hub_rbac_user_role;

CREATE TABLE iot_hub_rbac_user_role(
	id int(11) primary key auto_increment not null,
    user_id int(11),
    role_id int(11),
    is_deleted tinyint(1),
    create_time datetime NOT NULL,
    update_time timestamp NOT NULL
);

INSERT INTO iot_hub_rbac_user_role(id, user_id, role_id,create_time, update_time) VALUES (8, 108, 1,'2021-1-26 10:20:23','2021-1-26 10:20:25');