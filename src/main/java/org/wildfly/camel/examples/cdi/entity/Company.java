package org.wildfly.camel.examples.cdi.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "companies", schema = "idm_schema")
public class Company extends BaseEntity<Long> {
	@Column(name="name")
    private String name;
    
    @Column(name="name_clr")
    private String nameClr;

    @Column(name="commercial_register")
    private String commercialRegister;

    @Column(name="phone_number")
    private String phoneNumber;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;
    
    @Column(name="is_deleted", nullable = false)
    private Boolean isDeleted = false;
    
    @Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;
    
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameClr() {
		return nameClr;
	}

	public void setNameClr(String nameClr) {
		this.nameClr = nameClr;
	}

	public String getCommercialRegister() {
		return commercialRegister;
	}

	public void setCommercialRegister(String commercialRegister) {
		this.commercialRegister = commercialRegister;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "Company [name=" + name + ", nameClr=" + nameClr + ", commercialRegister=" + commercialRegister
				+ ", phoneNumber=" + phoneNumber + ", isDeleted=" + isDeleted + ", createdBy=" + createdBy
				+ ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + "]";
	}
}
