package org.wildfly.camel.examples.cdi.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subscriptions", schema = "public")
public class PostgresSubscription {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private Long id;
	
    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "bundle_id")
    private Long bundleId;
    
    @Column(name = "deducted_by")
    private Long deductedBy;
    
    @Column(name="start_date")
    private Date startDate;

    @Column(name="end_date")
    private Date endDate;

    @Column(name="paid_amount")
    private Float paidAmount;

    @Column(name="paid_amount_letters")
    private String paidAmountLetters;

    @Column(name="subscription_period")
    private Short subscriptionPeriod;

    @Column(name="receipt_number")
    private String receiptNumber;

    @Column(name="renew_subscription_flag")
    private Boolean renewSubscriptionFlag;  

    @Column(name="created_from_location_id")
    private Short createdFromLocationId;

    @Column(name="updated_from_location_id")
    private Short updatedFromLocationId;

    @Column(name="discount")
    private Short discount;
    
    @Column(name = "request_source_id")
    private Long requestSourceId;
    
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
    
    @Column(name="created_by")
    private Long createdBy;

    @Column(name="created_on")
    private LocalDateTime createdOn;

    @Column(name="updated_by")
    private Long updatedBy;

    @Column(name="updated_on")
    private LocalDateTime updatedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Long getBundleId() {
		return bundleId;
	}

	public void setBundleId(Long bundleId) {
		this.bundleId = bundleId;
	}

	public Long getDeductedBy() {
		return deductedBy;
	}

	public void setDeductedBy(Long deductedBy) {
		this.deductedBy = deductedBy;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Float getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Float paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getPaidAmountLetters() {
		return paidAmountLetters;
	}

	public void setPaidAmountLetters(String paidAmountLetters) {
		this.paidAmountLetters = paidAmountLetters;
	}

	public Short getSubscriptionPeriod() {
		return subscriptionPeriod;
	}

	public void setSubscriptionPeriod(Short subscriptionPeriod) {
		this.subscriptionPeriod = subscriptionPeriod;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public Boolean getRenewSubscriptionFlag() {
		return renewSubscriptionFlag;
	}

	public void setRenewSubscriptionFlag(Boolean renewSubscriptionFlag) {
		this.renewSubscriptionFlag = renewSubscriptionFlag;
	}

	public Short getCreatedFromLocationId() {
		return createdFromLocationId;
	}

	public void setCreatedFromLocationId(Short createdFromLocationId) {
		this.createdFromLocationId = createdFromLocationId;
	}

	public Short getUpdatedFromLocationId() {
		return updatedFromLocationId;
	}

	public void setUpdatedFromLocationId(Short updatedFromLocationId) {
		this.updatedFromLocationId = updatedFromLocationId;
	}

	public Short getDiscount() {
		return discount;
	}

	public void setDiscount(Short discount) {
		this.discount = discount;
	}

	public Long getRequestSourceId() {
		return requestSourceId;
	}

	public void setRequestSourceId(Long requestSourceId) {
		this.requestSourceId = requestSourceId;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
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
		return "PostgresSubscription [id=" + id + ", vehicleId=" + vehicleId + ", bundleId=" + bundleId
				+ ", deductedBy=" + deductedBy + ", startDate=" + startDate + ", endDate=" + endDate + ", paidAmount="
				+ paidAmount + ", paidAmountLetters=" + paidAmountLetters + ", subscriptionPeriod=" + subscriptionPeriod
				+ ", receiptNumber=" + receiptNumber + ", renewSubscriptionFlag=" + renewSubscriptionFlag
				+ ", createdFromLocationId=" + createdFromLocationId + ", updatedFromLocationId="
				+ updatedFromLocationId + ", discount=" + discount + ", requestSourceId=" + requestSourceId
				+ ", isDeleted=" + isDeleted + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy="
				+ updatedBy + ", updatedOn=" + updatedOn + "]";
	}
}
