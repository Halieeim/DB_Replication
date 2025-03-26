package org.wildfly.camel.examples.cdi.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "accounts", schema = "idm_schema")
public class Account extends BaseEntity<Long> {
	@Column(name = "account_number", unique = true)
	private Long accountNumber;

	@Column(name = "account_year")
	private Short accountYear;

	@ManyToOne
	@JoinColumn(name = "account_type_id")
	private AccountTypeLkp accountTypeLkp;

	@Column(name = "is_deleted", nullable = false)
	private Boolean isDeleted = false;

	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Person person;

	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Company company;

	@Column(name = "is_foreigner")
	private Boolean isForeigner;

	@Column(name = "email")
	private String email;

	@Column(name = "user_id")
	private Long userId;

	@ManyToOne
	@JoinColumn(name = "request_source_id")
	private RequestSourceLkp requestSource;

	@ManyToOne
	@JoinColumn(name = "office_id")
	private Office office;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Short getAccountYear() {
		return accountYear;
	}

	public void setAccountYear(Short accountYear) {
		this.accountYear = accountYear;
	}

	public AccountTypeLkp getAccountTypeLkp() {
		return accountTypeLkp;
	}

	public void setAccountTypeLkp(AccountTypeLkp accountTypeLkp) {
		this.accountTypeLkp = accountTypeLkp;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Boolean getIsForeigner() {
		return isForeigner;
	}

	public void setIsForeigner(Boolean isForeigner) {
		this.isForeigner = isForeigner;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public RequestSourceLkp getRequestSource() {
		return requestSource;
	}

	public void setRequestSource(RequestSourceLkp requestSource) {
		this.requestSource = requestSource;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
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
		return "Account [accountNumber=" + accountNumber + ", accountYear=" + accountYear + ", accountTypeLkp="
				+ accountTypeLkp + ", isDeleted=" + isDeleted + ", isForeigner=" + isForeigner + ", email=" + email
				+ ", userId=" + userId + ", requestSource=" + requestSource + ", createdBy=" + createdBy
				+ ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + "]";
	}
	
	
}
