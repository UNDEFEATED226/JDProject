drop table IF EXISTS iot_hub_rbac_organization;

CREATE TABLE iot_hub_rbac_organization(
	id int(10) PRIMARY KEY AUTO_INCREMENT,
    org_name varchar(64),
    parent_org_id int(11),
    org_level int(11),
    org_type int(11),
    org_type_name varchar(64),
    org_catlog int(11),
    base_org_code varchar(64), 
    tenant_id varchar(64),
    update_time datetime,
    create_time datetime,
    is_deleted tinyint(4),
    full_parent_id varchar(255),
    is_have_child tinyint(1)
) 
;
INSERT INTO iot_hub_rbac_organization VALUES (1, '上海繁易信息科技股份有限公司', NULL, NULL, NULL, NULL, NULL, NULL, '333', NULL, '2021-01-14 10:30:01', 0, NULL, NULL);


