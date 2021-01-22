drop table IF EXISTS iot_hub_rbac_organization;

CREATE TABLE `iot_hub_rbac_organization` (
	`id`INT(10) PRIMARY KEY,
    `org_name` VARCHAR(64),
    `parent_org_id` INT(11),
    `org_level` INT(11),
    `org_type` INT(11),
    `org_type_name` VARCHAR(64),
    `org_catlog` INT(11),
    `base_org_code` VARCHAR(64), 
    `tenant_id` VARCHAR(64),
    `update_time` DATETIME,
    `create_time` DATETIME,
    `is_deleted` TINYINT(4),
    `full_parent_id` VARCHAR(255),
    `is_have_child` TINYINT(1)
)DEFAULT CHARSET=utf8;

INSERT INTO `iot_hub_rbac_organization` (`id`, `org_name`, `parent_org_id`, `org_level`, `org_type`, `org_type_name`, `org_catlog`, `base_org_code`, `tenant_id`, `update_time`, `create_time`, `is_deleted`, `full_parent_id`, `is_have_child`) VALUES
(1, '上海繁易信息科技股份有限公司', NULL, NULL, NULL, NULL, NULL, NULL, '333', NULL, '2021-01-14 10:30:01', 0, NULL, NULL);