package org.wildfly.camel.examples.cdi.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vehicles", schema = "public")
public class Vehicle extends BaseEntity<Long> {
	@Column(name = "vehicle_type_id")
	private Long vehicleTypeId;
	
	@Column(name = "account_id")
	private Long accountId;
	
	@Column(name = "plate_country")
	private String plateCountry;
	
	@Column(name = "plate_letters")
	private String plateLetters;
	
	@Column(name = "plate_numbers")
	private String plateNumbers;
	
	@Column(name = "tag_value")
	private String tagValue;
	
	@Column(name = "is_deleted")
	private Boolean isDeleted;
	
	@Column(name = "is_old_plate")
	private Boolean isOldPlate;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;
	
	@Column(name = "governorate_lkp_id")
	private Long governorateLkpId;
	
	@Column(name = "migration_flag")
	private Boolean migrationFlag;
	
	@Column(name = "deliver_tag")
	private Boolean deliverTag;
	
	@Column(name = "request_source_id")
	private Long requestSourceId;
	
	@Column(name = "office_id")
	private Long officeId;

	public Long getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Long vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getPlateCountry() {
		return plateCountry;
	}

	public void setPlateCountry(String plateCountry) {
		this.plateCountry = plateCountry;
	}

	public String getPlateLetters() {
		return plateLetters;
	}

	public void setPlateLetters(String plateLetters) {
		this.plateLetters = plateLetters;
	}

	public String getPlateNumbers() {
		return plateNumbers;
	}

	public void setPlateNumbers(String plateNumbers) {
		this.plateNumbers = plateNumbers;
	}

	public String getTagValue() {
		return tagValue;
	}

	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getIsOldPlate() {
		return isOldPlate;
	}

	public void setIsOldPlate(Boolean isOldPlate) {
		this.isOldPlate = isOldPlate;
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

	public Long getGovernorateLkpId() {
		return governorateLkpId;
	}

	public void setGovernorateLkpId(Long governorateLkpId) {
		this.governorateLkpId = governorateLkpId;
	}

	public Boolean getMigrationFlag() {
		return migrationFlag;
	}

	public void setMigrationFlag(Boolean migrationFlag) {
		this.migrationFlag = migrationFlag;
	}

	public Boolean getDeliverTag() {
		return deliverTag;
	}

	public void setDeliverTag(Boolean deliverTag) {
		this.deliverTag = deliverTag;
	}

	public Long getRequestSourceId() {
		return requestSourceId;
	}

	public void setRequestSourceId(Long requestSourceId) {
		this.requestSourceId = requestSourceId;
	}

	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + getId() + ", vehicleTypeId=" + vehicleTypeId + ", accountId=" + accountId + ", plateCountry="
				+ plateCountry + ", plateLetters=" + plateLetters + ", plateNumbers=" + plateNumbers + ", tagValue="
				+ tagValue + ", isDeleted=" + isDeleted + ", isOldPlate=" + isOldPlate + ", createdBy=" + createdBy
				+ ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn
				+ ", governorateLkpId=" + governorateLkpId + ", migrationFlag=" + migrationFlag + ", deliverTag="
				+ deliverTag + ", requestSourceId=" + requestSourceId + ", officeId=" + officeId + "]";
	}
}
