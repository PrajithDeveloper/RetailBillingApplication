package com.projects.retailapp.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="SaleDetailsTbl")
public class SaleDetailsTbl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SaleDetailsID")
	private Long saleDetailsID;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="saleID")
	private SalesTbl salesTbl;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="itemID")
	private ItemTbl itemTbl;
	
	@NotNull
	@Column(name="Qty")
	private Integer qty;
	
	@NotNull
	@Column(name="total")
	private Long total;
	
	@NotNull
	@Column(name="wefDate")
	private LocalDateTime wefDate;

	public LocalDateTime getWefDate() {
		return wefDate;
	}

	public void setWefDate(LocalDateTime wefDate) {
		this.wefDate = wefDate;
	}

	public Long getSaleDetailsID() {
		return saleDetailsID;
	}

	public void setSaleDetailsID(Long saleDetailsID) {
		this.saleDetailsID = saleDetailsID;
	}

	public SalesTbl getSalesTbl() {
		return salesTbl;
	}

	public void setSalesTbl(SalesTbl salesTbl) {
		this.salesTbl = salesTbl;
	}

	public ItemTbl getItemTbl() {
		return itemTbl;
	}

	public void setItemTbl(ItemTbl itemTbl) {
		this.itemTbl = itemTbl;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
	
}
