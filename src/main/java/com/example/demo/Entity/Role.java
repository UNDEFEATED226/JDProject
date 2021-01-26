package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "iot_hub_rbac_user_role")
public class Role {
	@Id
	private Long id;

	@Column(name = "user_id")
	private Long userid;

	@Column(name = "role_id")
	private Long roleid;

}
