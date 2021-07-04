package com.projects.retailapp.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="SalesTbl")
public class SalesTbl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="saleID")
	private Long saleID;
		
	@NotNull
	@Column(name = "WefDate")
	private LocalDateTime wefDate;
	
	

	@Column(name="CustomerName")
	private String customerName;
	
	@Column(name="Phone")
	private String phone;
	
	@NotNull
	@Column(name="TotalItems")
	private Integer totalItems;
	
	@NotNull
	@Column(name="TotalAmount")
	private Long totalAmount;
	
	@Transient
	private List<SaleDetailsTbl> saleDetailsTbls;
	
	public List<SaleDetailsTbl> getSaleDetailsTbls() {
		return saleDetailsTbls;
	}

	public void setSaleDetailsTbls(List<SaleDetailsTbl> saleDetailsTbls) {
		this.saleDetailsTbls = saleDetailsTbls;
	}

	public Integer getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}

	
	
	/*
	 * @Transient private List<SaleDetailsTbl> saleDetailsTbls;
	 * 
	 * public List<SaleDetailsTbl> getSaleDetailsTbls() { return saleDetailsTbls; }
	 * 
	 * public void setSaleDetailsTbl(List<SaleDetailsTbl> saleDetailsTbls) {
	 * this.saleDetailsTbls = saleDetailsTbls; }
	 */

	public Long getSaleID() {
		return saleID;
	}

	public void setSaleID(Long saleID) {
		this.saleID = saleID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public LocalDateTime getWefDate() {
		return wefDate;
	}

	public void setWefDate(LocalDateTime wefDate) {
		this.wefDate = wefDate;
	}
	

}
