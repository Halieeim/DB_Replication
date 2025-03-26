package org.wildfly.camel.examples.cdi.entity;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "persons", schema = "idm_schema")
@AttributeOverride(name = "id", column = @Column(name = "account_id"))
public class Person extends BaseEntity<Long> {
	@Column(name="name")
    private String name;
    
    @Column(name="name_clr")
    private String nameClr;

    @Column(name="national_number")
    private String nationalNumber;

    @Column(name="mobile_number")
    private String mobileNumber;

    @OneToOne
    @MapsId
    @JoinColumn(name ="account_id")
    private Account account;

    @Column(name="passport_number")
    private String passportNumber;

    @Column(name="mobile_otp")
    private String mobileOtp;
    
    @Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

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

	public String getNationalNumber() {
		return nationalNumber;
	}

	public void setNationalNumber(String nationalNumber) {
		this.nationalNumber = nationalNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getMobileOtp() {
		return mobileOtp;
	}

	public void setMobileOtp(String mobileOtp) {
		this.mobileOtp = mobileOtp;
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
		return "Person [name=" + name + ", nameClr=" + nameClr + ", nationalNumber=" + nationalNumber
				+ ", mobileNumber=" + mobileNumber + ", account_id=" + account.getId() + ", passportNumber=" + passportNumber
				+ ", mobileOtp=" + mobileOtp + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy="
				+ updatedBy + ", updatedOn=" + updatedOn + "]";
	}
}
