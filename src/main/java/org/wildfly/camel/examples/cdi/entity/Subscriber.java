package org.wildfly.camel.examples.cdi.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Subscribers")
public class Subscriber {
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "TagValue")
	private String tagValue;
	
	@Column(name = "CarPlateNo")
	private String carPlateNo;
	
	@Column(name = "NationalId")
	private String nationalId;
	
	@Column(name = "PhoneNumber")
	private String phoneNumber;
	
	@Column(name = "Status")
	private int status = 2; // set default to 2 as confirmed record
	
	@Column(name = "VehicleTypeId")
	private Long vehicleTypeId;
	
	@Column(name = "UserId")
	private Long userId;
	
	@Column(name = "CreatedAt")
	private Timestamp createdAt;
	
	@Column(name = "UpdatedAt")
	private Timestamp updatedAt;
	
	@Column(name = "DeletedAt")
	private Timestamp deletedAt;
	
	@Column(name = "IsForeigner")
	private Boolean isForeigner;
	
	@Column(name = "IsCarPlateNoHasBeenReused")
	private Boolean isCarPlateNoHasBeenReused = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTagValue() {
		return tagValue;
	}

	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

	public String getCarPlateNo() {
		return carPlateNo;
	}

	public void setCarPlateNo(String carPlateNo) {
		this.carPlateNo = carPlateNo;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Long vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Timestamp getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Timestamp deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Boolean getIsForeigner() {
		return isForeigner;
	}

	public void setIsForeigner(Boolean isForeigner) {
		this.isForeigner = isForeigner;
	}

	public Boolean getIsCarPlateNoHasBeenReused() {
		return isCarPlateNoHasBeenReused;
	}

	public void setIsCarPlateNoHasBeenReused(Boolean isCarPlateNoHasBeenReused) {
		this.isCarPlateNoHasBeenReused = isCarPlateNoHasBeenReused;
	}

	@Override
	public String toString() {
		return "Subscriber [id=" + id + ", name=" + name + ", tagValue=" + tagValue + ", carPlateNo=" + carPlateNo
				+ ", nationalId=" + nationalId + ", phoneNumber=" + phoneNumber + ", status=" + status
				+ ", vehicleTypeId=" + vehicleTypeId + ", userId=" + userId + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt + ", isForeigner=" + isForeigner
				+ ", isCarPlateNoHasBeenReused=" + isCarPlateNoHasBeenReused + "]";
	}
	
	
}
