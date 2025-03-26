package org.wildfly.camel.examples.cdi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="request_source_lkp" , schema  = "common_schema")
public class RequestSourceLkp extends BaseEntity<Long>{
	@Column(name="`code`")
	private String code;

	@Column(name="`desc`")
	private String desc;
	
	@Column(name="`desc_en`")
	private String descEn;
}
