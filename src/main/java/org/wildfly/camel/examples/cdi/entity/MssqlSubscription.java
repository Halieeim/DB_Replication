package org.wildfly.camel.examples.cdi.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subscriptions")
public class MssqlSubscription {
	@Id
    @Column(name = "Id")
    private Long id;
    
	@Column(name = "subscriberid")
	private Integer subscriberId;

	@Column(name = "startdate")
	private Date startDate;

	@Column(name = "enddate")
	private Date endDate;

	@Column(name = "price")
	private Double price;

	@Column(name = "paymenttypeid")
	private Integer paymentTypeId = 1;

	@Column(name = "userid")
	private Integer userId;

	@Column(name = "billnumber")
	private Integer billNumber;

	@Column(name = "isactive")
	private Boolean isActive = true;

	@Column(name = "isrenewal")
	private Boolean isRenewal = false;

	@Column(name = "packageid")
	private Integer packageId;

	@Column(name = "discount")
	private Double discount;

	@Column(name = "discountispercentage")
	private Boolean discountIsPercentage = true;

	@Column(name = "discountbytheorderof")
	private String discountByTheOrderOf;
	
	@Column(name = "createdat")
    private Timestamp createdAt;

    @Column(name = "updatedat")
    private Timestamp updatedAt;

    @Column(name = "deletedat")
    private Timestamp deletedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Integer paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(Integer billNumber) {
		this.billNumber = billNumber;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsRenewal() {
		return isRenewal;
	}

	public void setIsRenewal(Boolean isRenewal) {
		this.isRenewal = isRenewal;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Boolean getDiscountIsPercentage() {
		return discountIsPercentage;
	}

	public void setDiscountIsPercentage(Boolean discountIsPercentage) {
		this.discountIsPercentage = discountIsPercentage;
	}

	public String getDiscountByTheOrderOf() {
		return discountByTheOrderOf;
	}

	public void setDiscountByTheOrderOf(String discountByTheOrderOf) {
		this.discountByTheOrderOf = discountByTheOrderOf;
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

	@Override
	public String toString() {
		return "MssqlSubscription [id=" + id + ", subscriberId=" + subscriberId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", price=" + price + ", paymentTypeId=" + paymentTypeId + ", userId="
				+ userId + ", billNumber=" + billNumber + ", isActive=" + isActive + ", isRenewal=" + isRenewal
				+ ", packageId=" + packageId + ", discount=" + discount + ", discountIsPercentage="
				+ discountIsPercentage + ", discountByTheOrderOf=" + discountByTheOrderOf + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt + "]";
	}
}
