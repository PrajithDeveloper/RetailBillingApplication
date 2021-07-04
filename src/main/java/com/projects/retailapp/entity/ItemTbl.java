package com.projects.retailapp.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ItemTbl")
public class ItemTbl {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ItemID")
	private Long itemID;
	
	
	@Column(name="WefDate")
	private LocalDateTime wefdate;
	
	@NotEmpty
	@Column(name = "Barcode", length = 25)
	private String barcode;
	
	@NotEmpty
	@Column(name = "Name", length = 100)
	private String name;
	
	@NotNull
	@Column(name = "Mrp")
	private Long mrp;
	
	@NotNull
	@Column(name = "Rate")
	private Long rate;
	
	@NotNull
	@Column(name = "Stock")
	private Integer stock;
		
	
	public Long getItemID() {
		return itemID;
	}
	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}
	public LocalDateTime getWefdate() {
		return wefdate;
	}
	public void setWefdate(LocalDateTime wefdate) {
		this.wefdate = wefdate;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getMrp() {
		return mrp;
	}
	public void setMrp(Long mrp) {
		this.mrp = mrp;
	}
	public Long getRate() {
		return rate;
	}
	public void setRate(Long rate) {
		this.rate = rate;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	//Default Constructor
	public ItemTbl() {
		
	}
	//this constructor is for deserialization of JSON for the property itemID
	public ItemTbl(Long itemID) {
		super();
		this.itemID = itemID;
	}
	
}
