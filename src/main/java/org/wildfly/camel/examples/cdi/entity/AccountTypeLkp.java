package org.wildfly.camel.examples.cdi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="account_types_lkp", schema = "idm_schema")
public class AccountTypeLkp extends BaseEntity<Long>{
	@Column(name="`code`")
	private String code;

	@Column(name="`desc`")
	private String desc;
	
	@Column(name="`desc_en`")
	private String descEn;
}
